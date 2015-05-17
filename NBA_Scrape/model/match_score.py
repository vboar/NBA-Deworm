# -*- coding: utf-8 -*-
# 比赛小节得分(比赛编号、小节、主队得分、客队得分)

class MatchScore(object):

	# 限制Match_score的属性
	__slots__ = (
		'__game_id','__section','__home_point','__guest_point'
	)
	def __init__(self):
		self.__game_id = None
		self.__section = -1
		self.__home_point = -1
		self.__guest_point = -1

	# 比赛编号
	@property
	def game_id(self):
		return self.__game_id
	
	@game_id.setter
	def game_id(self, value):
		self.__game_id = value
	
	# 小节
	@property
	def section(self):
		return self.__section
	
	@section.setter
	def section(self, value):
		self.__section = value
	
	# 主队得分
	@property
	def home_point(self):
		return self.__home_point
		
	@home_point.setter
	def home_point(self, value):
		self.__home_point = value
		
	# 客队得分
	@property
	def guest_point(self):
		return self.__guest_point
		
	@guest_point.setter
	def guest_point(self, value):
		self.__guest_point = value
