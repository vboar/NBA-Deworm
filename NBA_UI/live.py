# -*- coding: utf-8 -*-

__author__ = 'Vboar'

import urllib2
import time
from lxml import html
import os
import json
import urllib

list_url = 'http://v.opahnet.com/nba/tv/'
basic_url = 'http://g.hupu.com/nba/homepage/getMatchBasicInfo?matchId={0}'
live_url = 'http://g.hupu.com/node/playbyplay/matchLives?s_count=0&match_id={0}&homeTeamName={1}&awayTeamName={2}'
match_ids = []


def save_match_list():
    f = open('live/menu.txt', 'w')
    try:
        text = urllib2.urlopen(list_url).read()
        text = text.decode('utf8', 'ignore')
        tree = html.fromstring(text)
        match_list = tree.xpath('//div[@class="match-list"]/dl')
        for match in match_list:
            date_span = match.xpath('./dt/span[@class="date"]')
            _date = date_span[0].text_content() if date_span else ''
            day_span = match.xpath('./dt/span[@class="day"]')
            _day = day_span[0].text_content()[:3] if day_span else ''
            info_spans = match.xpath('./dd/span')
            _time = info_spans[0].text_content().strip()
            _teams = info_spans[1].text_content().strip()
            _info = info_spans[2].text_content().strip()
            links = match.xpath('./dd/a[@class="link1"]')
            _match_id = int(links[0].get('href').rsplit('.', 1)[0].rsplit('_', 1)[1])
            match_ids.append(_match_id)
            f.write(_match_id.__str__() + ';' + _date.encode('utf8') + ';' + _day.encode('utf8') + ';'
                    + _time.encode('utf8') + ';' + _teams.encode('utf8') + ';' + _info.encode('utf8') + '\n')
            f1 = open('live/' + _match_id.__str__() + '.txt', 'w')
            f1.close()
        f.close()
    except Exception as e:
        print 'error'
        print e
        f.close()
        pass


def save_match(mid):
    url = basic_url.format(mid)
    try:
        text = urllib2.urlopen(url).read()
        data = json.loads(text)
        tree = html.fromstring(data['html'])
        team_a = tree.xpath('//div[@class="team_vs_box"]/div[@class="team_a"]/div[@class="message"]/p/a')
        home_team = team_a[0].text_content().strip().encode('utf8')
        team_b = tree.xpath('//div[@class="team_vs_box"]/div[@class="team_b"]/div[@class="message"]/p/a')
        away_team = team_b[0].text_content().strip().encode('utf8')
        live = live_url.format(mid, urllib.quote(home_team), urllib.quote(away_team))
        live = live_url.format('150116', urllib.quote('勇士'), urllib.quote('火箭'))
        r = urllib2.urlopen(live)
        text = r.read().decode('utf8', 'ignore')
        msg = decode_messages(text)
        f = open('live/' + mid.__str__() + '.txt', 'w')
        for m in msg:
            f.write(m)
        f.close()
    except Exception as e:
        print 'error'
        print e

def decode_messages(text):
    try:
        tree = html.fromstring(text)
    except Exception as e:
        return []

    item_list = tree.xpath('//tr[@sid]')
    msg_list = []

    for item in item_list:
        sid = item.get('sid')
        tabs = item.xpath('.//td')
        if item.get('class') == 'pause' and len(tabs) == 1:
            content = tabs[0].text_content().encode('utf8', 'ignore')
            msg = sid + ';' + ';' + ';' + ';' + content + ';1' + '\n'
        elif len(tabs) == 4:
            residual = tabs[0].text_content()
            team = tabs[1].text_content().encode('utf8', 'ignore')
            a = tabs[2].xpath('.//b')
            if len(a) > 0:
                content = tabs[2].text_content().encode('utf8', 'ignore') + ';1'
            else:
                content = tabs[2].text_content().encode('utf8', 'ignore') + ';0'
            scores = tabs[3].text_content()
            msg = sid + ';' + residual + ';' + scores + ';' + team + ';' + content + '\n'
        else:
            continue
        msg_list.append(msg)

    return msg_list


def create_folder():
    if os.path.isdir('live'):
        pass
    else:
        os.mkdir('live')

if __name__ == '__main__':
    create_folder()
    while True:
        save_match_list()
        for match_id in match_ids:
            save_match(match_id)
        print 'update...'
        time.sleep(2)