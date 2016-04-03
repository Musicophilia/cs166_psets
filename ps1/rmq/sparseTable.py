logUpMap = []
logDownMap = []
powerMap = [] #initialized too big, just to be safe

numElems = 10
curPowTwo = 1
pow = 0
for i in xrange(numElems + 1): 
    if i == 0:
        logUpMap.append(pow)
        powerMap.append(0)
    elif (i == curPowTwo * 2):
        powerMap.append(curPowTwo)
        curPowTwo *= 2
        pow += 1
        logUpMap.append(pow)
    else:
        logUpMap.append(pow + 1)
    
    logDownMap.append(pow)

print [i for i in xrange(numElems + 1)]
print logUpMap
print logDownMap
print powerMap