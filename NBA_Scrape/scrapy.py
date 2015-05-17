# -*- coding: utf-8 -*-

import urllib2
import re
import time
import os

main_url = 'http://www.basketball-reference.com/'
match_url = main_url + 'boxscores/'


def begin():
    print u'NBA数据采集程序 V1.0'
    print '===================='
    print u'是否需要爬取比赛数据？[Y/N]'
    get_match = raw_input('> ')
    if get_match == 'Y':
        print u'请输入需要爬取的赛季：[2013-2014,2014-2015]'
        get_season = raw_input('> ')
    else:
        get_season = ''
    print u'是否需要爬取球队数据？[Y/N]'
    get_team = raw_input('> ')
    print u'是否需要爬取球员数据？[Y/N]'
    get_player = raw_input('> ')
    if get_match == 'Y':
        seasons = get_season.split(',')
        for season in seasons:
            scrape_match(season)
    check_player()
    if get_team == 'Y':
        pass
    if get_player == 'Y':
        pass
    print u'数据爬虫已结束，自动退出程序...'


def scrape_match(season):
    path = 'data/matches/' + season
    if os.path.isdir(path):
        pass
    else:
        os.mkdir(path)
    f_matches = open(path + '.txt', 'w')
    start_year = int(season[0:4])
    for year in range(start_year, start_year + 2):
        for month in range(1, 13):
            for day in range(1, 32):
                if year == start_year:
                    if month < 10:
                        break
                if year == start_year + 1:
                    if month > 6:
                        break
                print year, month, day
                url = match_url + 'index.cgi?month=' + month.__str__() + '&day=' + day.__str__() + '&year=' \
                      + year.__str__()
                response = urllib2.urlopen(url)
                items = re.findall(r'<td class="align_right bold_text"><a href="/boxscores/(.*?)\.html">',
                                   response.read())
                if len(items) != 0:
                    print items
                for item in items:
                    f_matches.write(item + '\n')
                    scrape_each_match(item, path)
    f_matches.close()


def scrape_each_match(item, path):
    url = match_url + item + '.html'
    response = urllib2.urlopen(url)
    html = response.read()
    result = re.search('<td><a href="/boxscores/' + item + r'.html">(.*?)<br>(.*?)</a>', html)
    guest_team = result.groups()[0]
    home_team = result.groups()[1]
    game_id = item + '-' + guest_team
    result = re.search('Lost', html)
    if result is None:
        regular = False
    else:
        regular = True
    result = re.search(r'<td class="align_center padding_bottom small_text" colspan="2">(.*?)<br>(.*?)</td>', html)
    if result is None:
        result = re.search(r'<td class="align_center padding_bottom small_text" colspan="2">(.*?)</td>', html)
        date = result.groups()[0]
        location = ''
    else:
        date = result.groups()[0]
        location = result.groups()[1]
    result = re.findall(r'<tr>\n<td><a href="/teams.*?">.*?</a></td>([\s\S]*?)</tr>', html)
    item = result[0].replace('\n', '')
    guest_point = re.findall(r'">(.*?)</td>', item)
    item = result[1].replace('\n', '')
    home_point = re.findall(r'">(.*?)</td>', item)
    result = re.search('<tr><td class="bold_text">Time of Game:</td><td>(.*?)</td></tr>', html)
    if result is None:
        time_of_game = ''
    else:
        time_of_game = result.groups()[0]
    f_match = open(path + '/' + game_id, 'w')
    f_player = open('data/players/players.txt', 'a')
    f_match.write(game_id + ';' + home_team + ';' + guest_team + ';' + str(regular) + ';' + date + ';' + location
                   + ';' + time_of_game + '\n')
    for item in guest_point:
        f_match.write(item + ';')
    f_match.write('\n')
    for item in home_point:
        f_match.write(item + ';')
    f_match.write('\n')

    result = re.findall(r'<th data-stat="header_tmp" align="center" colspan=20  class="bold_text over_header"'
                        r' >Basic Box Score Stats</th>[\s\S]*?<tr class="">([\s\S]*?)</tfoot>', html)
    i = 0
    for item in result:
        if i == 0:
            f_match.write('Guest Basic\n')
        else:
            f_match.write('Home Basic\n')
        item = item.replace('\n', '')
        res = re.findall(r'<tr  class=".*?">(.*?)</tr>', item)
        for item in res:
            res = re.search(r'<td align="left".*?><a href="(.*?)">(.*?)</a></td>', item)
            if res is None:
                name = 'Team Totals'
            else:
                href = res.groups()[0]
                name = res.groups()[1]
                f_player.write(name + ';' + href + '\n')
            res = re.findall(r'<td align="right".*?>(.*?)</td>', item)
            f_match.write(name + ';')
            if len(res) > 0:
                for item in res:
                    f_match.write(item + ';')
                f_match.write('\n')
            else:
                f_match.write('\n')
        i = 1

    result = re.findall(r'<th data-stat="header_tmp" align="center" colspan=15  class="bold_text over_header"'
                        r' >Advanced Box Score Stats</th>[\s\S]*?<tr class="">([\s\S]*?)</tfoot>', html)
    i = 0
    for item in result:
        if i == 0:
            f_match.write('Guest Advanced\n')
        else:
            f_match.write('Home Advanced\n')
        item = item.replace('\n', '')
        res = re.findall(r'<tr  class=".*?">(.*?)</tr>', item)
        for item in res:
            res = re.search(r'<td align="left".*?><a href="(.*?)">(.*?)</a></td>', item)
            if res is None:
                name = 'Team Totals'
            else:
                name = res.groups()[1]
            res = re.findall(r'<td align="right".*?>(.*?)</td>', item)
            f_match.write(name + ';')
            if len(res) > 0:
                for item in res:
                    f_match.write(item + ';')
                f_match.write('\n')
            else:
                f_match.write('\n')
        i = 1
    f_player.close()
    f_match.close()
    time.sleep(0.2)


def check_player():
    f = open('data/players/players.txt', 'r')
    lines = f.readlines()
    lines = list(set(lines))
    f.close()
    f = open('data/players/players.txt', 'w')
    for line in lines:
        f.write(line)
    f.close()


def scrape_player():
    f = open('data/players/players.txt', 'r')
    lines = f.readlines()
    players = {}
    for line in lines:
        line = line.strip('\n')
        temp = line.split(';')
        players[temp[0]] = temp[1]
    for player in players:
        scrape_each_player(player, players[player])


def scrape_each_player(name, url):
    url = main_url + url[1:]
    print name, url


def create_file():
    if os.path.isdir('data'):
        pass
    else:
        os.mkdir('data')
    if os.path.isdir('data/matches'):
        pass
    else:
        os.mkdir('data/matches')
    if os.path.isdir('data/players'):
        pass
    else:
        os.mkdir('data/players')
    if os.path.isdir('data/teams'):
        pass
    else:
        os.mkdir('data/teams')

if __name__ == '__main__':
    # create_file()
    # begin()
    check_player()
    scrape_player()