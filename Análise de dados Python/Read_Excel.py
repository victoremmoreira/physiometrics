import xlrd #Biblioteca para abrir arquivo .xlsx
import xlwt #Biblioteca para escrever um arquivo de Excel
import numpy as np #Biblioteca para criar vetor e matriz

hr_max = 160
hr_min = 50
tempoSensor = []
lista_HR1mm = []
lista_HR10seg = []
lista_HR1mim = []

#Carregando arquivo .xlsx (Não abre .xls)
workbook = xlrd.open_workbook('Projeto__Participante__Coleta__n_ - Copia.xlsx')
worksheet = workbook.sheet_by_index(0)

print("Carregando Dados do xlsx")
for row_num in range(worksheet.nrows):
    if row_num == 0:
        continue
    #Coloca em row_HR os valores da coluna 3 (Batimentos cardíacos)
    row_HR = worksheet.col_values(colx=4)
    row_GSR = worksheet.col_values(colx=6)
    row_EMG = worksheet.col_values(colx=8)
    #Contagem de tempo através das linhas dos dados
    tempo = np.array([row_num+1])
    tempoSeg = int(tempo/10)
    tempoMin = tempoSeg/60

print("Dados de HR Carregados")
print("Total de dados:",row_num+1, "| Tempo Total em segundos: ",tempoSeg,"seg", "| Tempo Total em Minutos: ",tempoMin,"mim")
print("HR antes ",row_HR[0:20])


#comando = str(input('Digite o comando: '))
for ajHR in range (0,row_num+1):
    #Modificar o valor da row_HR
    if (row_HR[ajHR] > hr_max) or (row_HR[ajHR] < hr_min):
        #Verificar o valor da (1) linha seguinte
       if (row_HR[ajHR+1] > hr_min) and (row_HR[ajHR+1] < hr_max):
           row_HR[ajHR] = row_HR[ajHR+1]
        #Verificar o valor da (2) linha seguinte
       elif (row_HR[ajHR+2] > hr_min) and (row_HR[ajHR+2] < hr_max):
           row_HR[ajHR] = row_HR[ajHR+2]
       #Verificar o valor da (1) linha anterior
       elif (row_HR[ajHR-1] > hr_min) and (row_HR[ajHR-1] < hr_max):
           row_HR[ajHR] = row_HR[ajHR-1]
       else:
           #Print oq não conseguiu mudar
            print(row_HR[ajHR],'não foi mudado, na linha: ',ajHR)
print("HR depois",row_HR)

#Escala de Dados
hr_1seg = 0
hr_10seg = 0
hr_1mim = 0
listaInicio = 0
listaFim =10


#Somando 10 linhas em um valor (10 linhas = 1 segundo)
while listaFim < row_num:
    for somaHr10mm in range (listaInicio,listaFim):
        if (listaFim < row_num):
            hr_1seg += row_HR[somaHr10mm]/10
            hr_1seg = int(hr_1seg)
            #print("Somando Linhas ",somaHr10mm)
    listaFim+=10
    listaInicio+=10

    lista_HR1mm.insert(somaHr10mm,hr_1seg)
    #print("Resultado da Soma:",hr_1seg, "| Até a linha:", somaHr10mm)
    hr_1seg=0

print("1mm","row_num ", row_num, "| hr_1seg: ", hr_1seg, "| listaFim: ", listaFim, "| lista_HR1mm:", lista_HR1mm)


#Somando 10 segundos em um valor (10 segundos = 1/6 de minuto)
listaFim=10
listaInicio=0

if (listaFim > len(lista_HR1mm)+1):
    print("Dados insuficientes para média 10 segundos")

while listaFim < len(lista_HR1mm)+1:
    for somaHr10seg in range (listaInicio,listaFim):
        if (listaFim < len(lista_HR1mm)+1):
            hr_10seg += lista_HR1mm[somaHr10seg]/10
            hr_10seg = int(hr_10seg)
            #print("Somando Linhas ",somaHr10seg,hr_10seg)
    listaFim+=10
    listaInicio+=10

    lista_HR10seg.insert(somaHr10seg,hr_10seg)
    #print("Resultado da Soma:",hr_10seg, "| Até a linha:", somaHr10seg)
    hr_10seg=0

print("10seg|","len(lista_HR1mm) ", len(lista_HR1mm), "| hr_10seg: ", hr_10seg, "| listaFim: ", listaFim, "| lista_HR10seg:", lista_HR10seg)

#Somando 60 segundos em um valor (60 segundos = 1 minuto)
#listaFim=6
#listaInicio=0

#if (listaFim > len(lista_HR10seg)+1):
#    print("Dados insuficientes para média 1 minuto")
#while listaFim < len(lista_HR10seg)+1:
#    print("1")
#    for somaHr1mim in range (listaInicio,listaFim):
#        print("2")
#        if (listaFim < len(lista_HR10seg)+1):
#            hr_1mim += lista_HR1mim[somaHr1mim]/6
#            hr_1mim = int(hr_1mim)
            #print("Somando Linhas ",somaHr1mim,hr_1mim)
#    listaFim+=1
#    listaInicio+=1

#    lista_HR1mim.insert(somaHr1mim,hr_1mim)
#    print("Resultado da Soma:",hr_1mim, "| Até a linha:", somaHr1mim)
#    hr_1mim=0

#print("1mim|","len(lista_HR10seg) ", len(lista_HR10seg), "| hr_1mim: ", hr_1mim, "| listaFim: ", listaFim, "| somaHr1mim:", somaHr1mim)


#Exportando Excel
exportData = xlwt.Workbook()
worksheet = exportData.add_sheet('Dados analisados com Python')#Normalização de dados

#Dados da planilha
worksheet.write(0,0,'Tempo(10mm)')
worksheet.write(0,1,'HR')
worksheet.write(0,2,'GSR')
worksheet.write(0,3,'EMG')

t=1
for k in range(0,row_num+1):
    worksheet.write(k+1,0,t)
    worksheet.write(k+1,1,row_HR[k])
    worksheet.write(k+1,2,row_GSR[k])
    worksheet.write(k+1,3,row_EMG[k])
    t+=1
    k+=1

worksheet = exportData.add_sheet('Média por Segundo')#Média de dados

#Dados da planilha
worksheet.write(0,0,'Tempo(1seg)')
worksheet.write(0,1,'HR')
worksheet.write(0,2,'GSR')
worksheet.write(0,3,'EMG')

aff=0
t=1
for u in range(0,len(lista_HR1mm)):
    worksheet.write(u+1,0,t)
    worksheet.write(u+1,1,lista_HR1mm[aff])
    worksheet.write(u+1,2,row_GSR[u])
    worksheet.write(u+1,3,row_EMG[u])
    u += 1
    aff += 1
    t+=1

worksheet = exportData.add_sheet('Média de 10seg')#Média de dados

#Dados da planilha
worksheet.write(0,0,'Tempo')
worksheet.write(0,1,'HR')
worksheet.write(0,2,'GSR')
worksheet.write(0,3,'EMG')

aff=0
t=10
for u in range(0,len(lista_HR10seg)):
    worksheet.write(u + 1, 0, t)
    worksheet.write(u+1, 1, lista_HR10seg[aff])
    worksheet.write(u+1, 2, row_GSR[u])
    worksheet.write(u+1, 3, row_EMG[u])
    u += 1
    aff += 1
    t+=10

worksheet = exportData.add_sheet('Média de 1mim')#Média de dados

#Dados da planilha
worksheet.write(0,0,'Tempo')
worksheet.write(0,1,'HR')
worksheet.write(0,2,'GSR')
worksheet.write(0,3,'EMG')

aff=0
t+=1
for u in range(0,len(lista_HR1mim)):
    worksheet.write(u + 1, 0, t)
    worksheet.write(u+1, 1, lista_HR1mim[aff])
    worksheet.write(u+1, 2, row_GSR[u])
    worksheet.write(u+1, 3, row_EMG[u])
    u += 1
    aff += 1
    t+=1

exportData.save('exportData.xls')#Finalizando arquivo .xls