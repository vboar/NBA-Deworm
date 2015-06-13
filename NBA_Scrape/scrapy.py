# -*- coding: utf-8 -*-

import urllib2
import re
import time
import os
import random

main_url = 'http://www.basketball-reference.com/'
match_url = main_url + 'boxscores/'

replayer = []

def begin():
    print u'NBA数据采集程序 V1.0'
    print '===================='
    print u'是否需要爬取比赛数据？[Y/N]'
    get_match = raw_input('> ')
    if get_match == 'Y':
        while True:
            print u'请输入需要爬取的比赛日期：[20141001-20141231]'
            get_date = raw_input('> ')
            if get_date == 'e':
                break
            scrape_match(get_date)
            check_player()
    print u'是否需要爬取球队基本数据？[Y/N]'
    get_team = raw_input('> ')
    print u'是否需要爬取球队具体数据？[Y/N]'
    get_team_data = raw_input('> ')
    print u'是否需要爬取球队Logo？[Y/N]'
    get_tlogo = raw_input('> ')
    print u'是否需要爬取球员数据？[Y/N]'
    get_player = raw_input('> ')
    print u'是否需要爬取在役球员头像？[Y/N]'
    get_now_pic = raw_input('> ')
    print u'是否需要爬取所有球员头像？[Y/N]'
    get_pic = raw_input('> ')
    check_player()
    if get_team == 'Y':
        get_team_list()
    if get_team_data == 'Y':
        scrape_team_by_local()
    if get_tlogo == 'Y':
        get_team_list()
        scrape_team_logo()
    if get_player == 'Y':
        scrape_player()
    if get_now_pic == 'Y':
        scrape_player_now_pic()
    if get_pic == 'Y':
        scrape_player_pic()
    print u'数据爬虫已结束，自动退出程序...'


def scrape_match(date):

    start_year = int(date[0:4])
    start_month = int(date[4:6])
    start_day = int(date[6:8])
    end_year = int(date[9:13])
    end_month = int(date[13:15])
    end_day = int(date[15:17])

    if start_month < 9:
        season = (start_year-1).__str__() + '-' + start_year.__str__()
    else:
        season = start_year.__str__() + '-' + (start_year+1).__str__()

    path = 'data/matches/' + season
    if os.path.isdir(path):
        pass
    else:
        os.mkdir(path)

    f_matches = open(path + '.txt', 'a')

    for year in range(start_year, end_year+1):
        for month in range(1, 13):
            if year == start_year and month < start_month:
                continue
            if year == end_year and month > end_month:
                continue
            for day in range(1, 32):
                if year == start_year and month == start_month and day < start_day:
                    continue
                if year == end_year and month == end_month and day > end_day:
                    continue
                print year, month, day
                url = match_url + 'index.cgi?month=' + month.__str__() + '&day=' + \
                      day.__str__() + '&year=' + year.__str__()
                response = urllib2.urlopen(url)
                items = re.findall(r'<td class="align_right bold_text"><a href="/boxscores/(.*?)\.html">',
                                   response.read())
                if len(items) != 0:
                    print items
                for item in items:
                    f_matches.write(item + '\n')
                    scrape_each_match(item, path)
                    time.sleep(0.2)
    f_matches.close()
    pass


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
    f_match.write(game_id + ';' + home_team + ';' + guest_team + ';' + str(regular) + ';' + date + ';' +
                  location + ';' + time_of_game + '\n')
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


def check_player():
    f = open('data/players/players.txt', 'r')
    lines = f.readlines()
    lines = list(set(lines))
    lines.sort()
    f.close()
    f = open('data/players/players.txt', 'w')
    for line in lines:
        f.write(line)
    f.close()


def scrape_player():
    f = open('data/players/players.txt', 'r')
    lines = f.readlines()
    for line in lines:
        line = line.strip('\n')
        temp = line.split(';')
        scrape_each_player(temp[0], temp[1])
        time.sleep(0.2)

    f.close()


def scrape_each_player(name, url):
    url = main_url + url[1:]
    response = urllib2.urlopen(url)
    html = response.read()
    if os.path.isfile('data/players/text/' + name):
        name = name + '-' + random.randint(1, 9).__str__()
        replayer.append(name)
    f = open('data/players/text/' + name, 'w')
    print name
    position = re.search(r'Position:</span> (.*?)&nbsp', html).groups()[0]
    result = re.search(r'Shoots:</span> (.*?)<br>', html)
    if result is None:
        shoots = ''
    else:
        shoots = result.groups()[0]
    born = re.search(r'data-birth="(.*?)"', html).groups()[0]
    result = re.search(r'</span> in (.*?)<a .*?>(.*?)</a>', html)
    if result is not None:
        hometown = result.groups()[0] + result.groups()[1]
    else:
        hometown = ''
    height = re.search(r'Height:</span> (.*?)&nbsp', html).groups()[0]
    weight = re.search(r'Weight:</span> (.*?) lbs', html).groups()[0]
    result = re.search(r'High School:</span> (.*?)<a .*?>(.*?)</a>', html)
    if result is None:
        result = re.search(r'High School:</span> (.*?)\n', html)
        if result is None:
            high_school = ''
        else:
            high_school = result.groups()[0]
    else:
        high_school = result.groups()[0] + result.groups()[1]
    result = re.search(r'College:</span> <a .*?>(.*?)</a>', html)
    if result is None:
        college = ''
    else:
        college = result.groups()[0]
    result = re.search(r'Draft:</span> <a .*?>(.*?)</a>(.*?)<a .*?>(.*?)</a>', html)
    if result is None:
        draft = ''
    else:
        draft = result.groups()[0] + result.groups()[1] + result.groups()[2]
    result = re.search(r'NBA Debut:</span> <a .*?">(.*?)</a>', html)
    if result is None:
        debut = ''
    else:
        debut = result.groups()[0]
    result = re.search(r'Experience:</span> (.*?) years', html)
    if result is None:
        experience = '-1'
    else:
        experience = result.groups()[0]
    result = re.findall(r'<span style=\'font-size.*?>(.*?)</span>', html)
    if result is None or len(result) == 0:
        number = ''
    else:
        number = result[len(result)-1]
    f.write(name + ';' + position + ';' + shoots + ';' + born + ';' + hometown + ';' +
            height + ';' + weight + ';' + high_school + ';' + college + ';' + draft +
            ';' + debut + ';' + experience + ';' + number + ';' + '\n')

    result = re.findall(r'<div class="table_container" id="(.*?)">([\s\S]*?)</table>', html)
    for line in result:
        if line[0] == 'div_totals':
            f.write('Totals\n')
        elif line[0] == 'div_per_game':
            f.write('Per Game\n')
        elif line[0] == 'div_advanced':
            f.write('Advanced\n')
        elif line[0] == 'div_playoffs_totals':
            f.write('Playoffs Totals\n')
        elif line[0] == 'div_playoffs_per_game':
            f.write('Playoffs Per Game\n')
        elif line[0] == 'div_playoffs_advanced':
            f.write('Playoffs Advanced\n')
        else:
            continue
        res = line[1].replace('\n', '')
        result = re.findall(r'<tr  class=".*?_table" id=".*?">([\s\S]*?)</tr>', res)
        for item in result:
            result = re.search(r'<a href="/players/.*?">(.*?)</a>', item)
            season = result.groups()[0][2:]
            result = re.search(r'<td align="left" ><a href="/teams/.*?">(.*?)</a></td>', item)
            if result is not None:
                team = result.groups()[0]
            else:
                team = 'TOT'
            pos = re.search(r'<td align="center" >(.*?)</td>', item).groups()[0]
            f.write(season + ';' + team + ';' + pos + ';')
            result = re.search(r'<td align="center" >.*?</td>(.*)', item, re.S).groups()[0]
            result = re.findall(r'<td align="right" .*?>(.*?)</td>', result)
            for temp in result:
                f.write(temp + ';')
            f.write('\n')
        result = re.search(r'<tr  class=" stat_total">(.*?)</tr>', res).groups()[0]
        result = re.findall(r'<td align="right" >(.*?)</td>', result)
        f.write('Career;;')
        for item in result:
            f.write(item + ';')
        f.write('\n')


    result = re.search(r'<div class="table_container" id="div_salaries">([\s\S]*?)</table>', html)
    if result is not None:
        f.write('Salaries\n')
        res = result.groups()[0].replace('\n', '')
        result = re.search(r'<tbody>(.*?)</tbody>', res).groups()[0]
        result = re.findall(r'<tr  class="">(.*?)</tr>', result)
        for item in result:
            temp = re.search(r'<td align="left" >(.*?)</td>', item)
            season = temp.groups()[0][2:]
            temp = re.search(r'<td align="left" ><a href="/teams/(.*?)/.*?</td>', item)
            team = temp.groups()[0]
            temp = re.search(r'<td align="right"  csk=".*?">(.*?)</td>', item)
            salary = temp.groups()[0]
            f.write(season + ';' + team + ';' + salary + ';\n')
        result = re.search(r'<tr  class=" stat_total">(.*?)</tr>', res).groups()[0]
        season = re.search(r'<td align="left" >(.*?)</td>', result).groups()[0]
        salary = re.search(r'<td align="right".*?>(.*?)</td>', result).groups()[0]
        f.write(season + ';;' + salary + ';\n')
    f.close()
    print 'replayer is: ', replayer


def scrape_team():
    teams = get_team_list()
    for item in teams:
        scrape_each_team(item[1], item[0], item[2])
        time.sleep(0.2)


def scrape_team_by_local():
    f = open('data/teams/teams.txt', 'r')
    teams = f.readlines()
    for item in teams:
        item = item.replace('\n', '')
        temp = item.split(';')
        scrape_each_team(temp[0], temp[1], temp[2])
        time.sleep(0.2)


def scrape_each_team(name, abbr, year):
    print name, abbr
    f = open('data/teams/text/' + abbr, 'w')
    url = main_url + 'teams/' + abbr
    response = urllib2.urlopen(url)
    html = response.read()
    result = re.search(r'Location:</span> (.*?)\n', html)
    location = result.groups()[0]
    record = re.search(r'Record:</span> (.*?)\n', html).groups()[0]
    result = re.search(r'\((.*?) NBA', record)
    if result is None:
        record = re.search(r'(.*?),', record).groups()[0]
    else:
        record = result.groups()[0]
    playoff_appearance = re.search(r'Playoff Appearances:</span> (.*?)\n', html).groups()[0]
    result = re.search(r'\((.*?) NBA', playoff_appearance)
    if result is None:
        pass
    else:
        playoff_appearance = result.groups()[0]
    championships = re.search(r'Championships:</span> (.*?)\n', html).groups()[0]
    result = re.search(r'\((.*?) NBA', championships)
    if result is None:
        pass
    else:
        championships = result.groups()[0]

    league = ''
    division = ''
    url2 = 'http://www.nba.com/teams/'
    html2 = urllib2.urlopen(url2).read()
    result = re.search(r'<span class="nbaNavSubheading">Eastern Conference</span>([\S\s]*?)</div>', html2)
    result = result.groups()[0].replace('\n', '')
    result = re.findall(r'<span class="nbaDivision">(.*?)</span>([\S\s]*?)</ul>', result)
    for item in result:
        temp = re.search(abbr, item[1])
        if abbr == 'NJN':
            temp = re.search('BKN', item[1])
        if abbr == 'PHO':
            temp = re.search('PHX', item[1])
        if temp is not None:
            league = 'E'
            division = item[0]
            break
        else:
            pass
    result = re.search(r'<span class="nbaNavSubheading">Western Conference</span>([\S\s]*?)</div>', html2)
    result = result.groups()[0].replace('\n', '')
    result = re.findall(r'<span class="nbaDivision">(.*?)</span>([\S\s]*?)</ul>', result)
    for item in result:
        temp = re.search(abbr, item[1])
        if abbr == 'NJN':
            temp = re.search('BKN', item[1])
        if abbr == 'PHO':
            temp = re.search('PHX', item[1])
        if temp is not None:
            league = 'W'
            division = item[0]
            break
        else:
            pass
    f.write(name + ';' + abbr + ';' + year + ';' + location + ';' + record + ';' +
            playoff_appearance + ';' + championships + ';' + league + ';' + division + ';\n')

    f.write('Totals\n')
    url = main_url + 'teams/' + abbr + '/stats_totals.html'
    html = urllib2.urlopen(url).read()
    result = re.search(r'<div class="table_container" id="div_team_stats_totals">([\S\s]*?)</div>', html)
    result = re.findall(r'<tr  class="">[\S\s]*?<td align="left".*?><a href="(.*?)">'
                        r'(.*?)</a></td>([\S\s]*?)</tr>', result.groups()[0])
    sites = []
    for item in result:
        season = item[1][2:]
        if 'BBA' in item[2] or 'ABA' in item[2]:
            break
        f.write(season + ';')
        b = [season, item[0]]
        sites.append(b)
        item = re.findall(r'<td align="right" >(.*?)</td>', item[2])
        count = 0
        for temp in item:
            if count == 6:
                break
            a = re.search(r'<span .*?>(.*?)</span>', temp)
            if a is None:
                pass
            else:
                temp = a.groups()[0]
            f.write(temp + ';')
            count += 1
        f.write('\n')

    for site in sites:
        f.write(site[0] + '\n')
        url = main_url + site[1][1:]
        print url
        html = urllib2.urlopen(url).read()
        result = re.search(r'<div class=".*?" id="div_team_stats">([\S\s]*?)</div>', html).groups()[0]
        result = result.replace('\n', '')
        result = re.findall(r'<td align="left" >(.*?)</td>(.*?)</tr>', result)
        for item in result:
            if item[0] == 'Team' or item[0] == 'Team/G' or item[0] == 'Opponent' or item[0] == 'Opponent/G':
                result = re.findall(r'<td align="right" >(.*?)</td>', item[1])
                for temp in result:
                    f.write(temp + ';')
                f.write('\n')
        result = re.search(r'<div class=".*?" id="div_team_misc">([\S\s]*?)</div>', html).groups()[0]
        result = result.replace('\n', '')
        result = re.search(r'<td align="left" >Team</td>(.*?)</tr>', result).groups()[0]
        result = re.findall(r'<td align=".*?" >(.*?)</td>', result)
        for item in result:
            f.write(item + ';')
        f.write('\n')

        time.sleep(0.2)
    f.close()


def get_team_list():
    f = open('data/teams/teams.txt', 'w')
    url = main_url + 'teams/'
    response = urllib2.urlopen(url)
    html = response.read()
    result = re.search(r'<div class="table_container p402_hide " id="div_active">([\s\S]*?)</div>', html)
    result = result.groups()[0].replace('\n', '')
    result = re.findall(r'<td align="left" ><a href="/teams/(.*?)/">(.*?)</a></td>[\s\S]*?'
                        r'<td align="right" >(.*?)</td>', result)
    for item in result:
        f.write(item[1] + ';' + item[0] + ';' + item[2] + ';\n')
    f.close()
    return result


def scrape_team_logo():
    f = open('data/teams/teams.txt', 'r')
    lines = f.readlines()
    for line in lines:
        temp = line.replace('\n', '').split(';')
        abbr = temp[1]
        print abbr
        url = 'http://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/' + abbr + '.png'
        if abbr == 'NOH':
            url = 'http://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/no.png'
        if abbr == 'UTA':
            url = 'http://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/utah.png'
        response = urllib2.urlopen(url)
        f_logo = open('data/teams/logo/' + abbr + '.png', 'wb')
        f_logo.write(response.read())
        f_logo.close()
        time.sleep(0.2)


def scrape_player_now_pic():
    url = 'http://espn.go.com/nba/players'
    response = urllib2.urlopen(url)
    html = response.read()
    result = re.findall(r'<a style="padding-top:5px;padding-left:0px;" href="(.*?)">', html)
    for item in result:
        url = 'http://espn.go.com' + item
        response = urllib2.urlopen(url)
        html = response.read()
        results = re.findall(r'<td class="sortcell"><a href="(.*?)">(.*?)</a>', html)
        for temp in results:
            url = temp[0]
            name = temp[1]
            print name
            response = urllib2.urlopen(url)
            html = response.read()
            temps = re.search(r'<meta property="og:image" content="(.*?)&h=.*?" />', html)
            if temps is not None:
                url = temps.groups()[0]
                response = urllib2.urlopen(url)
                pic = open('data/players/now_pic/' + name + '.png', 'wb')
                pic.write(response.read())
                pic.close()
            time.sleep(0.2)
    pass


def scrape_player_pic():
    f_player = open('data/players/players.txt', 'r')
    players = f_player.readlines()
    for player in players:
        item = player.replace('\n', '').split(';')
        name = item[0]
        print name
        url = 'http://www.basketball-reference.com' + item[1]
        response = urllib2.urlopen(url)
        html = response.read()
        result = re.search(r'<img .*? src="(.*?)" alt=', html)
        if result is not None:
            url = result.groups()[0]
            response = urllib2.urlopen(url)
            f = open('data/players/pic/' + name + '.png', 'wb')
            f.write(response.read())
            f.close()
            time.sleep(0.2)


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
    if os.path.isdir('data/players/text'):
        pass
    else:
        os.mkdir('data/players/text')
    if os.path.isdir('data/players/now_pic'):
        pass
    else:
        os.mkdir('data/players/now_pic')
    if os.path.isdir('data/players/pic'):
        pass
    else:
        os.mkdir('data/players/pic')
    if os.path.isdir('data/teams'):
        pass
    else:
        os.mkdir('data/teams')
    if os.path.isdir('data/teams/logo'):
        pass
    else:
        os.mkdir('data/teams/logo')
    if os.path.isdir('data/teams/text'):
        pass
    else:
        os.mkdir('data/teams/text')

if __name__ == '__main__':
    create_file()
    begin()