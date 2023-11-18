def suureneja(n, järjend=[]):
    if n == 11:
        print(järjend+[n])
    else:
        suureneja(n+1, järjend+[n])
        if n <=5:
            suureneja(n*2, järjend+[n])

suureneja(3)

def töögraafik(n, ülid, graafik=[], puhkuseid = 0):
    if n==0:
        print(graafik)
    else:
        for ül in ülid:
            if ül=='puhkus' and puhkuseid < 2:
                töögraafik(n-1, ülid, graafik+[ül], puhkuseid + 1)
            else:
                töögraafik(n-1, ülid, graafik+[ül], puhkuseid)

töögraafik(4, ['puhkus', 'proge', 'debug'])