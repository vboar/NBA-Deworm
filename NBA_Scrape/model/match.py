# -*- coding: utf-8 -*-
# 比赛信息类(比赛编号、日期、比赛地点、主客队缩写、主客队得分、比赛时长)
class Match(object):

	# 限制Match的属性
	__slots__ = ('__game_id', '__date','__location','__home_team',
	'__home_point','__guest_team','__guest_point','__time'
	)

	def __init__(self):
		pass

	@property
	def game_id(self):
		return self.__game_id
	
	@game_id.setter
	def game_id(self, value):
		self.__game_id = value
		
	# 日期
	@property
	def date(self):
		return self.__date
	
	@date.setter
	def date(self,value):
		self.__date = value
	
	# 比赛地点
	@property
	def location(self):
		return self.__location
	
	@location.setter
	def location(self,value):
		self.__location = value
	
	# 主队缩写
	@property
	def home_team(self):
		return self.__home_team
		
	@home_team.setter
	def home_team(self, value):
		self.__home_team = value
		
	# 主队得分
	@property
	def home_point(self):
		return self.__home_point
	
	@home_point.setter
	def home_point(self, value):
		self._home_point = value
	
	# 客队缩写
	@property
	def guest_team(self):
		return self.__guest_team
	
	@guest_team.setter
	def guest_team(self, value):
		self.__guest_team = value
	
	# 客队得分
	@property
	def guest_point(self):
		return self._guest_point
		
	@guest_point.setter
	def guest_point(self, value):
		self.__guest_point = value
		
	@property
	def time(self):
		return self.__time
	
	@time.setter
	def time(self, value):
		self.__time = value
	
