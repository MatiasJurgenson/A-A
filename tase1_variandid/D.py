def numbrid(n,järjend=[], numberid=[7,8,9]):
    if n == 0 and järjend[-3:] != [7,8,9]:
          print(järjend)
    else:
        if järjend[-3:] != [7,8,9]:
            if len(järjend) == 0:
                numbrid(n-1, järjend+[7], numberid) 
            elif len(järjend) == 1:
                numbrid(n-1, järjend+[8], numberid)
            else:
                for number in numberid:
                    numbrid(n-1, järjend+[number], numberid)
                 

def kohvik(raha, päevi, toidud, hinnad, valik = []):
    if päevi == 0 and raha >=0:
        print(valik)
    elif raha >=0:
        for i, toit in enumerate(['eimidagi'] + toidud):
                if toit == 'eimidagi':
                    kohvik(raha, päevi-1,toidud,hinnad, valik+['eimidagi'])
                else:
                    kohvik(raha-hinnad[i-1], päevi-1,toidud,hinnad, valik+[toit])

numbrid(5)

kohvik(5,2, ['saiake', 'praad', 'supp'], [2,5,3])



[7, 8, 7, 7, 7]
[7, 8, 7, 7, 8]
[7, 8, 7, 7, 9]
[7, 8, 7, 8, 7]
[7, 8, 7, 8, 8]
[7, 8, 7, 9, 7]
[7, 8, 7, 9, 8]
[7, 8, 7, 9, 9]
[7, 8, 8, 7, 7]
[7, 8, 8, 7, 8]
[7, 8, 8, 7, 9]
[7, 8, 8, 8, 7]
[7, 8, 8, 8, 8]
[7, 8, 8, 8, 9]
[7, 8, 8, 9, 7]
[7, 8, 8, 9, 8]
[7, 8, 8, 9, 9]