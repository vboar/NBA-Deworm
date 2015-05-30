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
    print u'是否需要爬取球队Logo？[Y/N]'
    get_tlogo = raw_input('> ')
    print u'是否需要爬取球员数据？[Y/N]'
    get_player = raw_input('> ')
    if get_match == 'Y':
        seasons = get_season.split(',')
        for season in seasons:
            scrape_match(season)
    check_player()
    if get_team == 'Y':
        scrape_team()
    if get_tlogo == 'Y':
        get_team_list()
        scrape_player()
    if get_player == 'Y':
        scrape_player()
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
        time.sleep(0.2)
    f.close()


def scrape_each_player(name, url):
    url = main_url + url[1:]
    response = urllib2.urlopen(url)
    html = response.read()
    f = open('data/players/text/' + name, 'w')

    position = re.search(r'Position:</span> (.*?)&nbsp', html).groups()[0]
    shoots = re.search(r'Shoots:</span> (.*?)<br>', html).groups()[0]
    born = re.search(r'data-birth="(.*?)"', html).groups()[0]
    result = re.search(r'</span> in (.*?)<a .*?>(.*?)</a>', html)
    hometown = result.groups()[0] + result.groups()[1]
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
    if result is None:
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
        result = re.findall(r'<tr  class="full_table" id=".*?">([\s\S]*?)</tr>', res)
        for item in result:
            result = re.search(r'<a href="/players/.*?">(.*?)</a>', item)
            season = result.groups()[0][2:]
            team = re.search(r'<td align="left" ><a href="/teams/.*?">(.*?)</a></td>', item).groups()[0]
            pos = re.search(r'<td align="center" >(.*?)</td>', item).groups()[0]
            f.write(season + ';' + team + ';' + pos + ';')
            result = re.search(r'<td align="center" >.*?</td>(.*)', item, re.S).groups()[0]
            result = re.findall(r'<td align="right" >(.*?)</td>', result)
            for temp in result:
                f.write(temp + ';')
            f.write('\n')
        result = re.search(r'<tr  class=" stat_total">(.*?)</tr>', res).groups()[0]
        result = re.findall(r'<td align="right" >(.*?)</td>', result)
        f.write('Career;;')
        for item in result:
            f.write(item + ';')
        f.write('\n')

    f.write('Salaries\n')
    result = re.search(r'<div class="table_container" id="div_salaries">([\s\S]*?)</table>', html)
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


def scrape_team():
    teams = get_team_list()
    for item in teams:
        scrape_each_team(item[1], item[0], item[2])
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


def get_team_logo():
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