def karjääripäev(n, sündmused, plaan = []):
    if n==0:
        print(plaan)
    elif n==1 and "lõunapaus" not in plaan: #vaja oli elif kuna muidu tekkis lõpmatu rekursioon
        print(plaan + ["lõunapaus"])
    else:
        for sündmus in sündmused:
            if sündmus == "lõunapaus": # selle asemel et lõunapaus eemaldada vaada kas praegu sündmus on "lõunapaus"
                if "lõunapaus" not in plaan: # kui pole plaanis siis lisatakse, muul juhul jäetakse vahele
                    karjääripäev(n-1, sündmused, plaan + [sündmus])
            else: # kui pole lõunapaus siis lisatakse sündmus
                karjääripäev(n-1, sündmused, plaan + [sündmus])



karjääripäev(3, ['ettekanne', 'töötube', 'lõunapaus'])