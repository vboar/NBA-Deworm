# -*- coding: utf-8 -*-

__author__ = 'Vboar'

import urllib2
import json

url = 'http://localhost/team?abbr=HOU'
text = urllib2.urlopen(url).read()
json_object = json.loads(text)

abbr = json_object['abbr']
info = json_object['info']
name = info['name']
location = info['location']
league = info['league']
division = info['division']
buildup_time = info['buildup_time']
record = info['record']
playeroff_appearance = info['playeroff_appearance']
championships = info['championships']

print u'球队：' + abbr
print u'球队名：' + name
print u'位置：' + location
print u'联盟：' + league
print u'分区：' + division
print u'成立时间：' + buildup_time
print u'历史：' + record
print u'参加季后赛次数：' + playeroff_appearance.__str__()
print u'获得冠军次数：' + championships.__str__()

