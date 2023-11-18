def bussipilet(min, soodus, valik = [], hind = 10):
    if soodus == []:
        print(valik, 'hinnaga', hind)
    else:
        bussipilet(min, soodus[1:], valik + [], hind)
        if hind *0.5 > min:
            bussipilet(min, soodus[1:], valik + [soodus[0]], hind*0.5) #point sama aga soodus[0] asemel oli lih [0]. mis polnud sama oli *0.5 hinna eest unustamine

bussipilet(2, ['pesionär', 'pime', 'kuldklient'] )