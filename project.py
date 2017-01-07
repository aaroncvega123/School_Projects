import csv
import numpy as np
import matplotlib.pyplot as plt

"""
    Code by Aaron Vega and Jensen Fernandez
"""

"""
    #4. 

This code will analyze Apple's stock from 12/04/2006 to 11/28/2016. 
The analysis will tell us in graphical format Apple's:
     1. 10 year performance
     2. Correlation to lag
     3. Percentage of mean
     4. 10 year moving average
     5. Volatility
     
It will also tell us Apple's:
    1. Average volume
    2. Average volatility
    3. Median volatility
    4. Average change between opening price and closing price
    5. Percent increase from 12/4/2006 to 11/28/2016
    
"""

"""
    This list sets up array "data" that will hold all the information
    from the excel file.  All values inside the array are in string
    format.
"""

data = open('AppleData.csv', 'r')
reader = csv.reader(data)
data = []
for row in reader:
    data.append(row)
data.reverse()  
data = np.array(data[:-1])


# 0. Date, 1. Open, 2. High, 3. Low, 4. Close 5. Volume, 6. Adj Close
#date = yyyy/mm/dd


"""
    Here begins a series of charts for req. #1.
    Array syntax is used throughout, satisfying req. #3
"""

#10 year stock performance and iPhone releases

"""
    The follow list is the date of every iPhone release convereted into
    float format.
"""
dates = [20070629, \
            20080711, \
            20090619, \
            20100624, \
            20111014, \
            20120921, \
            20131020, \
            20141019, \
            20150925, \
            20160331, \
            20160916]

"""
    The following block creates a 2D list, where each index
    has the index of the date of release and the stock price
    on that day.
    
    The purpose of this list is to plot the points of iPhone
    releases along the change in stock price.
"""
counter = 0
date = 0
majorReleases = []

for i in range(len(data) - 1):
    if date == 11:
        break;
    date1 = data[i,0].split('-')
    date1 = float(date1[0] + date1[1] + date1[2])
    date2 = data[i + 1,0].split('-')
    date2 = float(date2[0] + date2[1] + date2[2])
    if (dates[date] < date2) and (dates[date] >= date1):
        #print(str(counter) + " " + data[i,6])
        majorReleases.append([counter, data[i,6]])
        date += 1
    counter += 1

majorReleases = np.array(majorReleases)

plt.figure(1)
plt.plot(range(len(data)), data[:,6], color='b')
plt.plot(majorReleases[:,0], majorReleases[:,1], marker='d', color='r', linestyle='None')
plt.title("Apple's 10 performance")
plt.ylabel("Price")
plt.xlabel("Day (starting at " + data[0,0] + ")")
plt.legend(loc='best fit')
plt.show()
plt.savefig("Apple_closings")

#Lag corr-coef

def lagCorr(li, lags):
    """
        This function returns a list of correlations between a series
        and its shifted self, many times over.
    """
    li1 = li[:]
    li2 = li[:]
    ret = []
    for i in range(lags):
        if i == 0:
            ret.append(1)
        else:    
            ret.append(np.corrcoef(li1[i:], li2[:-i])[0,1])
    return ret

closingFloats = data[:,6].astype(float)

lagData = lagCorr(closingFloats, len(closingFloats)/2)

plt.figure(2)
plt.plot(range(len(lagData)), lagData)
plt.title("Correlation Coefficient vs. lag")
plt.ylabel("Correlation Coefficient")
plt.xlabel("Lag")
plt.show()
plt.savefig("Apple_CorrCoeff")

#Percentage of average

mean = np.mean(closingFloats)

plt.figure(3)
plt.plot(range(len(data)), closingFloats/mean, color='b')
plt.title("Apple's 10 year percentage of mean")
plt.ylabel("Price/Average ratio")
plt.xlabel("Day (starting at " + data[0,0] + ")")
plt.legend(loc='best fit')
plt.show()
plt.savefig("Apple_perOfAvg")

#Moving Average

def moving_average(input_array):
    """
    Takes the array as input and returns an array containing the three-day moving average
    
    Positional Input Parameters:
        input_array: 1D array
        
    Examples: 
        >>> moving_average(array([1,2,3,4,5,6]))
        array([2,3,4,5])
    """
    return (input_array[2:] + input_array[1:-1] + input_array[:-2]) / 3 
    
plt.figure(4)
plt.plot(range(len(data)-2), moving_average(np.ravel(closingFloats)))
plt.title("Apple's 10 year weekly moving average")
plt.ylabel("Price")
plt.xlabel("Day (starting at " + data[0,0] + ")")
plt.legend(loc='best fit')
plt.show()
plt.savefig("Apple_movingAvg")

#Volatility

high = data[:,2].astype(float)
low = data[:,3].astype(float)
close = data[:,4].astype(float)
openBid = data[:,1].astype(float)


volatility = (high - low)/high 

"""
    We measure volatility by taking the high,
    subtracting the low, and dividing that value by the high.
    We get the percentage the stocks price changed in the given day
"""

plt.figure(5)
plt.plot(range(len(data)),volatility)
plt.title("Apples 10 year volatility")
plt.ylabel("Value change")
plt.xlabel("Day (starting at " + data[0,0] + ")")
plt.legend(loc='best fit')
plt.show()
plt.savefig("Apple_volatility")

"""
    Here are the non-graphical analyses for requirement #2
"""

print("Average volume: " + str(round(np.mean(data[:,5].astype(float)))))
print("Average volatility: " + str(round(np.mean(volatility),2)))
print("Median volatility: " + str(round(np.median(volatility),2)))
print("Average change between opening price and closing price: " + str(round(np.mean(openBid - close),2)))
print("Percent increase from 12/4/2006 to 11/28/2016: " + str(round(((closingFloats[-1] - closingFloats[1])/closingFloats[1] * 100),2))+ str("%"))
