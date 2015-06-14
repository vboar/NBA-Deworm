# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
from scipy import stats
from matplotlib import pyplot as plt

# a = stats.norm.rvs(size=500, loc=0, scale=1)

f = file("stats/team_testing.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
win_home = datas[0]
win_guest = datas[1]
f = file("stats/ast.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
ast_home = datas[0]
ast_guest = datas[1]
f = file("stats/blk.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
blk_home = datas[0]
blk_guest = datas[1]
f = file("stats/pf.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
pf_home = datas[0]
pf_guest = datas[1]
f = file("stats/pts.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
pts_home = datas[0]
pts_guest = datas[1]
f = file("stats/stl.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
stl_home = datas[0]
stl_guest = datas[1]
f = file("stats/tov.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
tov_home = datas[0]
tov_guest = datas[1]
f = file("stats/trb.txt")
datas = np.loadtxt(f, delimiter=";", dtype=int)
trb_home = datas[0]
trb_guest = datas[1]

f = open('stats/team_result.txt', 'w')

# 生成PP图或QQ图，进行曲线拟合，初步进行正态性检验
home_result = stats.probplot(win_home, plot=plt)
plt.savefig('stats/home_probplot.png')
plt.close()
guest_result = stats.probplot(win_guest, plot=plt)
plt.savefig('stats/guest_probplot.png')
# 第1、2行：0是拟合直线斜率，1是截距，2是相关系数
f.write(home_result[1][0].__str__() + ';' + home_result[1][1].__str__() + ';' +
        home_result[1][2].__str__() + '\n')
f.write(guest_result[1][0].__str__() + ';' + guest_result[1][1].__str__() + ';' +
        guest_result[1][2].__str__() + '\n')

# 进行单总体KS检验，进行正态性检验
home_std = (win_home-np.mean(win_home))/np.std(win_home)
guest_std = (win_guest-np.mean(win_guest))/np.std(win_guest)
home_result = stats.kstest(home_std, 'norm')
guest_result = stats.kstest(guest_std, 'norm')
# 第3、4行：0是D值（KS test statistic），1是p-value
f.write(home_result[0].__str__() + ';' + home_result[1].__str__() + '\n')
f.write(guest_result[0].__str__() + ';' + guest_result[1].__str__() + '\n')

# 进行双总体KS检验，进行两总体（标准化后）同分布检验
result = stats.ks_2samp(home_std, guest_std)
# 第5行：0是D值，1是p-value
f.write(result[0].__str__() + ';' + result[1].__str__() + '\n')

# 进行双总体KS检验，进行两总体（原始）同分布检验
result = stats.ks_2samp(win_home, win_guest)
# 第6行：0是D值，1是p-value
f.write(result[0].__str__() + ';' + result[1].__str__() + '\n')


# 进行偏度峰度检验，进行正态性检验
home_result = stats.normaltest(win_home)
guest_result = stats.normaltest(win_guest)
home_skew = stats.skew(win_home)
home_kurtosis = stats.kurtosis(win_home)
guest_skew = stats.skew(win_guest)
guest_kurtosis = stats.kurtosis(win_guest)
# 第7、8行：0是偏度，1是峰度，2是s2+k2，3是p-value
# s^2 + k^2, where s is the z-score returned by skewtest and k is the z-score returned by kurtosistest.
f.write(home_skew.__str__() + ';' + home_kurtosis.__str__() + ';' +
        home_result[0].__str__() + ';' + home_result[1].__str__() + '\n')
f.write(guest_skew.__str__() + ';' + guest_kurtosis.__str__() + ';' +
        guest_result[0].__str__() + ';' + guest_result[1].__str__() + '\n')

# 进行基于配对数据的t检验，检验主客场胜场数差异
result = stats.ttest_rel(win_home, win_guest)
# 第9行：0是t值，1是p-value
f.write(result[0].__str__() + ';' + result[1].__str__() + '\n')
# 第10行:0是主场胜场数均值，1是主场胜场数标准差，2是客场均值，3是客场标准差
home_avg = np.mean(win_home)
home_std = np.std(win_home)
guest_avg = np.mean(win_guest)
guest_std = np.std(win_guest)
f.write(home_avg.__str__() + ';' + home_std.__str__() + ';' +
        guest_avg.__str__() + ';' + guest_std.__str__() + '\n')


# print stats.ranksums(home, guest)
# print stats.wilcoxon(home, guest)
# print stats.mannwhitneyu(home, guest)
