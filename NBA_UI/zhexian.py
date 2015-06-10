import numpy as np
import pylab as pl
import sys

data1 = sys.argv[1]
data2 = sys.argv[2]
data3 = sys.argv[3]
data4 = sys.argv[4]
data5 = sys.argv[5]

x = [2011,2012,2013,2014,2014]# Make an array of x values
y = [data1,data2,data3,data4,data5]# Make an array of y values for each x value
pl.plot(x, y)# use pylab to plot x and y
pl.xlim(2011, 2015)
pl.savefig(sys.path[0]+"/zhexian.png", dpi=50)
#pl.show()
# show the plot on the screen