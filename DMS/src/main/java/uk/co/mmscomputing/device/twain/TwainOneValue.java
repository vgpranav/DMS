package uk.co.mmscomputing.device.twain;

public class TwainOneValue extends TwainContainer{

// TWON_ONEVALUE. Container for one value.
// !!! Type can be TW_FRAME i.e. ICAP_FRAMES
/*
typedef struct {
   TW_UINT16  ItemType;
   TW_UINT32  Item;
} TW_ONEVALUE, FAR * pTW_ONEVALUE;
*/
  Object  item;

  TwainOneValue(int cap,byte[] container){
    super(cap,container);
    item=get32BitObjectAt(container,2);
  }

  @Override
int getType(){return TWON_ONEVALUE;};

  @Override
byte[] getBytes(){
    byte[] container=new byte[6];
    jtwain.setINT16(container,0,type);
    set32BitObjectAt(container,2,item);
    return container;
  }

  @Override
public Object getCurrentValue()throws TwainIOException{           return item;}
  @Override
public void   setCurrentValue(Object obj)throws TwainIOException{ item=obj;}

  @Override
public Object getDefaultValue()throws TwainIOException{           return item;}
  @Override
public void   setDefaultValue(Object obj)throws TwainIOException{ item=obj;}

  @Override
public Object[] getItems(){
    Object[] items=new Object[1];
    items[0]=item;
    return items;
  }

  @Override
public String toString(){
    String s=super.toString();
    s+="item         = "+item+"\n";
    return s;
  }
}