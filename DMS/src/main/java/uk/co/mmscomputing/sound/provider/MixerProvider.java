package uk.co.mmscomputing.sound.provider;

public class MixerProvider extends javax.sound.sampled.spi.MixerProvider{

  javax.sound.sampled.Mixer[]        mixers=null;
  javax.sound.sampled.Mixer.Info[]   infos=null;

  public MixerProvider(){
    mixers=new Mixer[1];
    infos=new Mixer.Info[1];
    mixers[0]=new Mixer();
    infos[0]=mixers[0].getMixerInfo();
  }

  @Override
public javax.sound.sampled.Mixer.Info[] getMixerInfo(){
    return infos;
  }

  @Override
public javax.sound.sampled.Mixer getMixer(javax.sound.sampled.Mixer.Info info){
    if(info.equals(infos[0])){
      return mixers[0];
    }
    throw new IllegalArgumentException();
  }
}