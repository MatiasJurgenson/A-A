def kahanevad(arv, järjend = ''):
    if arv == 0:
        print(järjend + "(0)")
    else:
        if not (arv - 3) < 0:
             kahanevad(arv-3, järjend + "("+ str(arv)+")")
        if not (arv - 1) < 0:
             kahanevad(arv-1, järjend + "("+str(arv)+")")

kahanevad(7)