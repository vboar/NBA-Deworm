# -*- coding: utf-8 -*-

import urllib2
import re
import time


br_url = 'http://www.basketball-reference.com/'
bs_url = br_url + 'boxscores/'

start = []
end = []


# 设置要获取的比赛的开始日期和结束日期
def set_start_and_end(starts, ends):
    start_array = starts.split('-')
    end_array = ends.split('-')
    for a in start_array:
        start.append(int(a))
    for a in end_array:
        end.append(int(a))


# 爬虫获取比赛ID
def get_match_id(start_date, end_date):
    match_ids = []
    for year in range(start_date[0], end_date[0]+1):
        for month in range(1, 13):
            for day in range(1, 32):
                if year == start_date[0] and month < start_date[1]:
                    continue
                if year == start_date[0] and month == start_date[1] and day < start_date[2]:
                    continue
                if year == end_date[0] and month > end_date[1]:
                    continue
                if year == end_date[0] and month == end_date[1] and day > end_date[2]:
                    continue
                url = bs_url + 'index.cgi?month=' + month.__str__() + '&day=' + day.__str__() + '&year=' \
                      + year.__str__()
                response = urllib2.urlopen(url)
                print url
                items = re.findall(r'<td class="align_right bold_text"><a href="/boxscores/(.*?)\.html">',
                                   response.read())
                if len(items) != 0:
                    print items
                for item in items:
                    match_ids.append(item)
                time.sleep(1)
    # 将获取的比赛ID写入文件中并且作为返回值返回
    f = open('data/match_ids.txt', 'w')
    for match_id in match_ids:
        f.write(match_id + '\n')
    f.close()
    return match_ids


# 从TXT中获取比赛ID
def get_match_ids_from_txt():
    matches_ids = []
    f = open('data/match_ids.txt', 'r')
    lines = f.readlines()
    for line in lines:
        matches_ids.append(line.replace('\n', ''))
    return matches_ids


# 获得每场比赛数据（顺便得到球员ID）
def get_match_data(match_id):
    home_team = match_id[-3:]
    url = bs_url + match_id + '.html'
    html = urllib2.urlopen(url).read()
    result = re.search(r'<td><a href="/boxscores/.*?">(.*?)<br>(.*?)</a>', html)
    team1 = result.groups()[0]
    team2 = result.groups()[1]
    print home_team, team1, team2

    time.sleep(1)
    pass

if __name__ == '__main__':
    print u'NBA数据采集程序'
    print u'请选择获取比赛ID的方式：'
    print u'1.从网站爬取并生成新的match_ids.txt'
    print u'2.从旧的match_ids.txt中获取'
    way = raw_input('> ')
    if way == '1':
        print u'请输入起始日期[如2015-05-15]：'
        start_str = raw_input('> ')
        print u'请输入结束日期[如2015-05-15]：'
        end_str = raw_input('> ')
        set_start_and_end(start_str, end_str)
        matches = get_match_id(start, end)
    else:
        matches = get_match_ids_from_txt()
    for match in matches:
        get_match_data(match)
    pass