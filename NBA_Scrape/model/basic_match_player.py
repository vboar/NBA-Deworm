# -*- coding: utf-8 -*-
# 单场比赛球员基本信息类（比赛编号、球员名字、所属球队、各类数据）
# 需不需要一个属性来记录是否为首发球员？冷板凳球员要记录咩？
class BasicMatchPlayer(object):

	# 限制MatchPlayer的属性
	__slots__ = ('__game_id', '__player_name','__team_abbr','__starter','__minute',
	'__fg','__fga','__fga_pct','__fg3a','__fg3','__fg3_pct','__ft','__fta','__fta_pct',
	'__orb','__drb','__trb','__ast','__blk','__stl','__tov','__pf','__pts','__plus_minus'
	)

	def __init__(self):
		pass
	
	# 比赛编号
	@property
	def game_id(self):
		return self.__game_id
	
	@gameId.setter
	def gameId(self, value):
		self._game_id = value
	
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
		
	# 上场时间
	@property
	def minute(self):
		return self.__minute
	
	@minute.setter
	def minute(self, value):
		self.__minute = value
	
	# 投篮命中数
	@property
	def fg(self):
		return self.__fg
	
	@fg.setter
	def fg(self, value):
		self.__fg = value
	
	# 投篮出手数
	@property
	def fga(self):
		return self.__fga
	
	@fga.setter
	def fga(self, value):
		self.__fga = value
	
	# 投篮命中率
	@property
	def fga_pct(self):
		return self.__fga_pct
		
	@fga_pct.setter
	def fga_pct(self, value):
		self.__fga_pct = value
		
	# 三分命中数
	@property
	def fg3(self):
		return self.__fg3
	
	@fg3.setter
	def fg3(self, value):
		self.__fg3 = value
	
	# 三分出手数
	@property
	def fg3a(self):
		return self.__fg3a
	
	@fg3a.setter
	def fg3a(self, value):
		self.__fg3a = value
	
	# 三分命中率
	@property
	def fg3_pct(self):
		return self.__fg3_pct
	
	@fg3_pct.setter
	def fg3_pct(self, value):
		self.__fg3_pct = value
		
	# 罚球命中数
	@property
	def ft(self):
		return self.__ft
	
	@ft.setter
	def ft(self, value):
	self.__ft = value
	
	# 罚球投篮数
	@property
	def fta(self):
		return self.__fta
	
	@fta.setter
	def fta(self, value):
		self.__fta = value
	
	# 罚球命中率
	@property
	def fta_pct(self):
		return self.__fta_pct
		
	@fta_pct.setter
	def fta_pct(self, value):
		self.__fta_pct = value
		
	# 进攻篮板
	@property
	def orb(self):
		return self.__orb
	
	@orb.setter
	def orb(self, value):
		self.__orb = value
		
	# 防守篮板
	@property
	def drb(self):
		return self.__drb
	
	@drb.setter
	def drb(self, value):
		self.__drb = value
		
	# 总篮板
	@property
	def trb(self):
		return self.__trb
		
	@trb.setter
	def trb(self, value):
		self.__trb = value
	
	# 助攻
	@property
	def ast(self):
		return self.__ast
	
	@ast.setter
	def ast(self, value):
		self.__ast = value
		
	# 抢断
	@property
	def stl(self):
		return self.__stl
		
	@stl.setter
	def stl(self, value):
		self.__stl = value
	
	# 盖帽
	@property
	def blk(self):
		return self.__blk
	
	@blk.setter
	def blk(self, value):
		self.__blk = value
	
	# 失误
	@property
	def tov(self):
		return self.__tov
	
	@tov.setter
	def tov(self, value):
		self.__tov = value
	
	# 犯规
	@property
	def pf(self):
		return self.__pf
	
	@pf.setter
	def pf(self, value):
		self.__pf = value
		
	# 得分
	@property
	def pts(self):
		return self.__pts
		
	@pts.setter
	def pts(self, value):
		self.__pts = value
		
	# +/-
	@property
	def plus_minus(self):
		return self.__plus_minus
	
	@plus_minus.setter
	def plus_minus(self, value):
		self.__plus_minus = value
