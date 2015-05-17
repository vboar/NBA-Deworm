# -*- coding: utf-8 -*-
# 单场比赛球员高阶信息类（比赛编号、球员名字、所属球队、各类数据）

class AdvancedMatchPlayer(object):
	
	# 限制MatchPlayer的属性
	__slots__ = ('__game_id', '__player_name','__team_abbr','__starter','__ts_pct',
		'__efg_pct','__fg3a_per_fga_pct','__fta_per_fga_pct','__orb_pct','__drb_pct',
		'__trb_pct','__ast_pct','__stl_pct','__tov_pct','__tov_pct','__usg_pct','__off_rtg','__def_rtg'
	)
	 
	# 比赛编号
	@property
	def game_id(self):
		return self.__gameId
	
	@gameId.setter
	def game_id(self,value):
		self.__gameId = value
	
	# 球员姓名
	@property
	def player_name(self):
		return self.__player_name
	
	@player_name.setter
	def player_name(self, value):
		self.__player_name = value
		
	# 球队缩写
	@property
	def team_abbr(self):
		return self.__team_abbr
	
	@team_abbr.setter
	def team_abbr(self,value):
		self.__team_abbr = value
		
	# 是否为首发
	@property
	def starter(self):
		return self.__starter
		
	@starter.setter
	def starter(self, value):
		self.__starter = value
		
	# 真实命中率
	@property
	def ts_pct(self):
		return self.__ts_pct
	
	@ts_pct.setter
	def ts_pct(self, value):
		self.__ts_pct = value
	
	# Effecitive Field Goal Percentage
	@property
	def efg_pct(self):
		return self.__efg_pct
	
	@efg_pct.setter
	def efg_pct(self, value):
		self.__efg_pct = value
	
	# 3-Point Attempt Rate
	@property
	def fg3a_per_fga_pct(self):
		return self.__fg3a_per_fga_pct
	
	@fg3a_per_fga_pct.setter
	def fg3a_per_fga_pct(self, value):
		self.__fg3a_per_fga_pct = value
	
	# Free Throw Attempt Rate
	@property
	def fta_per_fga_pct(self):
		return self.__fta_per_fga_pct
	
	@fta_per_fga_pct.setter
	def fta_per_fga_pct(self, value):
		self.__fta_per_fga_pct = value
	
	# 进攻篮板率
	@property
	def orb_pct(self):
		return self.__orb_pct
		
	@orb_pct.setter
	def orb_pct(self, value):
		self.__orb_pct = value
		
	# 防守篮板率
	@property
	def drb_pct(self):
		return self.__drb_pct
		
	@drb_pct.setter
	def drb_pct(self, value):
		self.__drb_pct = value
		
	# 总篮板率
	@property
	def trb_pct(self):
		return self.__trb_pct
		
	@trb_pct.setter
	def trb_pct(self,value):
		self.__trb_pct = value
	
	# 助攻率
	@property
	def ast_pct(self):
		return self.__ast_pct
	
	@ast_pct.setter
	def ast_pct(self, value):
		self.__ast_pct = value

	# 抢断率
	@property
	def stl_pct(self):
		return self.__stl_pct
	
	@stl_pct.setter
	def stl_pct(self, value):
		self.__stl_pct = value
		
	# 盖帽率
	@property
	def tov_pct(self):
		return self.__blk_pct
	
	@tov_pct.setter
	def tov_pct(self, value):
		self.__blk_pct = value
		
	# 失误率
	@property
	def tov_pct(self):
		return self.__tov_pct
	
	@tov_pct.setter
	def tov_pct(self, value):
		self.__tov_pct = value
		
	# 使用率
	@property
	def usg_pct(self):
		return self.__tov_pct
	
	@usg_pct.setter
	def usg_pct(self, value):
		self.__usg_pct = value
		
	# 进攻效率
	@property
	def off_rtg(self):
		return self.__off_rtg
	
	@off_rtg.setter
	def off_rtg(self, value):
		self.__off_rtg = value
		
	# 防守效率
	@property
	def def_rtg(self):
		return self.__def_rtg
	
	@def_rtg.setter
	def def_rtg(self, value):
		self.__def_rtg = value
