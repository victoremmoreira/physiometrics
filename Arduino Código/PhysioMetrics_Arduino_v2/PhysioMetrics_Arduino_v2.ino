  #include <Crescer.h> //Biblioteca pra contar tempo
#include <SoftwareSerial.h> //Biblioteca para Bluetooth

//Para Plotar no Arduino
Tempora tempP;
int maxV = 600;
int minV = 200;

//Comunicação com Bluetooth
SoftwareSerial serialBT (10, 11); // RX, TX
String textoRecebido = "";
unsigned long delay1 = 0;
unsigned long command = 0;

unsigned char pin = 13;//Led do Arduino
unsigned char counter=0;
unsigned int heart_rate=2; //Pino Digital 2 para HR
unsigned long temp[2];
unsigned long sub=0;
volatile unsigned char state = LOW;
bool data_effect=true;
const int max_heartpluse_duty=1000;
//you can change it follow your system's request.2000 meams 2 seconds. 
//System return error if the duty overtrip 2 second.

//GSR 3.3v
const int GSR = A0; //Pino analógico para o GSR
int thresholdGSR=0;
int sensorValueGSR;

//EMG 3.3v
const int EMG = A1; //Pino analógico para o EMG
int sensorValueEMG;


void setup(){
  Serial.begin(9600);
  serialBT.begin(115200);//Iniciando Porta do Bluetooth
  pinMode(pin, OUTPUT);
  array_init();
  attachInterrupt(0, interrupt, RISING);//set interrupt 0,digital port 2

//GSR
  long sum=0;
  for(int i=0;i<500;i++){ //500
    sensorValueGSR=analogRead(GSR);
    sum += sensorValueGSR;
//EMG
    sensorValueEMG=analogRead(EMG);
  }
  thresholdGSR = sum/500;
//Plotar Gráfico na IDE do Arduino
   tempP.defiSP(1000);// tempo do marcador = 1seg
}
void loop(){
  //GSR
  int temp;
  sensorValueGSR=analogRead(GSR);
  temp = thresholdGSR - sensorValueGSR;
  if(abs(temp)>50){// difereça maior q 50
    sensorValueGSR=analogRead(GSR);
    temp = thresholdGSR - sensorValueGSR;
  }
  
  //EMG
  sensorValueEMG=analogRead(EMG);
  
  digitalWrite(pin, state);
  
  //Informações para Java
  /*Serial.print(".HR.");
  Serial.print(heart_rate);
  Serial.print(".GSR.");
  Serial.print(sensorValueGSR);
  Serial.print(".EMG.");
  Serial.println(sensorValueEMG);*/
  
  //Plotter no Arduino
  if (tempP.Saida(1)){
    Serial.print(maxV);
    tempP.Saida(0);
  }else{
    Serial.print(minV);
  }
  Serial.print(" ");
  Serial.print(heart_rate);
  Serial.print(" ");
  Serial.print(sensorValueGSR);
  Serial.print(" ");
  Serial.print(analogRead(A1));
  Serial.println();

//Cominucação com Bluetooth
  serialBT.print("HR: ");
  serialBT.print(heart_rate);
  serialBT.print(",");
  serialBT.print(" GSR: ");
  serialBT.print(sensorValueGSR);
  serialBT.print(";");
  serialBT.print(" EMG: ");
  serialBT.println(sensorValueEMG); 
  delay (100);
}

void sum(){ //calculate the heart rate
  if(data_effect){
      heart_rate=60000/(temp[1]-temp[0]);//60*20*1000/20_total_time 
      //Serial.print("ECG:");
      //Serial.println(heart_rate);
  }
   data_effect=1;//sign bit
}
void interrupt(){
    temp[counter]=millis();
    state = !state;    
    //Serial.println(counter,DEC);
    //Serial.println(temp[counter]);
    switch(counter){
       case(0):
       sub=temp[counter]-temp[1];
       //Serial.println(sub);
       break;
       default:
       sub=temp[counter]-temp[counter-1];
       //Serial.println(sub);
       break;
      }
    if(sub>max_heartpluse_duty)//set 2 seconds as max heart pluse duty
      {
        data_effect=0;//sign bit
        counter=0;
        //Serial.println("Heart rate measure error,test will restart!" );
        array_init();
       }
    if (counter==1&&data_effect){
      counter=0;
      sum();
    }else if(counter!=1&&data_effect)
    counter++;
    else{
      counter=0;
      data_effect=1;
    }
}
void array_init(){
  for(unsigned char i=0;i!=1;++i){
    temp[i]=0;
  }
  temp[1]=millis();
}
