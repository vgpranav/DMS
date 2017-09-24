package com.dms.beans;

import java.util.Date;

public class BillParamData {
	
	private long billparamdataid;
	private long billstructureid;
	private double pcdelayval;
	private String pcdelaykey;
	private double nocval;
	private double op4w;
	private double op3w;
	private double op2w;
	private double sp4w;
	private double sp3w;
	private double sp2w;
	private double mtpsqft;
	private double shop;
	private double mt3p5bhk;
	private double mt3bhk;
	private double mt2p5bhk;
	private double mt2bhk;
	private double mt1p5bhk;
	private double mt1bhk;
	private double mt1rk;
	private String mttype;
	private String extraValues;
	private long createdby;
	private Date createdon;
	private int isactive;
	 
	public double getPcdelayval() {
		return pcdelayval;
	}
	public void setPcdelayval(double pcdelayval) {
		this.pcdelayval = pcdelayval;
	}
	public String getPcdelaykey() {
		return pcdelaykey;
	}
	public void setPcdelaykey(String pcdelaykey) {
		this.pcdelaykey = pcdelaykey;
	}
	public double getNocval() {
		return nocval;
	}
	public void setNocval(double nocval) {
		this.nocval = nocval;
	}
	public double getOp4w() {
		return op4w;
	}
	public void setOp4w(double op4w) {
		this.op4w = op4w;
	}
	public double getOp3w() {
		return op3w;
	}
	public void setOp3w(double op3w) {
		this.op3w = op3w;
	}
	public double getOp2w() {
		return op2w;
	}
	public void setOp2w(double op2w) {
		this.op2w = op2w;
	}
	public double getSp4w() {
		return sp4w;
	}
	public void setSp4w(double sp4w) {
		this.sp4w = sp4w;
	}
	public double getSp3w() {
		return sp3w;
	}
	public void setSp3w(double sp3w) {
		this.sp3w = sp3w;
	}
	public double getSp2w() {
		return sp2w;
	}
	public void setSp2w(double sp2w) {
		this.sp2w = sp2w;
	}
	public double getMtpsqft() {
		return mtpsqft;
	}
	public void setMtpsqft(double mtpsqft) {
		this.mtpsqft = mtpsqft;
	}
	public double getShop() {
		return shop;
	}
	public void setShop(double shop) {
		this.shop = shop;
	}
	public double getMt3p5bhk() {
		return mt3p5bhk;
	}
	public void setMt3p5bhk(double mt3p5bhk) {
		this.mt3p5bhk = mt3p5bhk;
	}
	public double getMt3bhk() {
		return mt3bhk;
	}
	public void setMt3bhk(double mt3bhk) {
		this.mt3bhk = mt3bhk;
	}
	public double getMt2p5bhk() {
		return mt2p5bhk;
	}
	public void setMt2p5bhk(double mt2p5bhk) {
		this.mt2p5bhk = mt2p5bhk;
	}
	public double getMt2bhk() {
		return mt2bhk;
	}
	public void setMt2bhk(double mt2bhk) {
		this.mt2bhk = mt2bhk;
	}
	public double getMt1p5bhk() {
		return mt1p5bhk;
	}
	public void setMt1p5bhk(double mt1p5bhk) {
		this.mt1p5bhk = mt1p5bhk;
	}
	public double getMt1bhk() {
		return mt1bhk;
	}
	public void setMt1bhk(double mt1bhk) {
		this.mt1bhk = mt1bhk;
	}
	public double getMt1rk() {
		return mt1rk;
	}
	public void setMt1rk(double mt1rk) {
		this.mt1rk = mt1rk;
	}
	public String getMttype() {
		return mttype;
	}
	public void setMttype(String mttype) {
		this.mttype = mttype;
	}
	public long getBillparamdataid() {
		return billparamdataid;
	}
	public void setBillparamdataid(long billparamdataid) {
		this.billparamdataid = billparamdataid;
	}
	public long getBillstructureid() {
		return billstructureid;
	}
	public void setBillstructureid(long billstructureid) {
		this.billstructureid = billstructureid;
	} 
	public String getExtraValues() {
		return extraValues;
	}
	public void setExtraValues(String extraValues) {
		this.extraValues = extraValues;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "BillParamData [billparamdataid=" + billparamdataid + ", billstructureid=" + billstructureid
				+ ", pcdelayval=" + pcdelayval + ", pcdelaykey=" + pcdelaykey + ", nocval=" + nocval + ", op4w=" + op4w
				+ ", op3w=" + op3w + ", op2w=" + op2w + ", sp4w=" + sp4w + ", sp3w=" + sp3w + ", sp2w=" + sp2w
				+ ", mtpsqft=" + mtpsqft + ", shop=" + shop + ", mt3p5bhk=" + mt3p5bhk + ", mt3bhk=" + mt3bhk
				+ ", mt2p5bhk=" + mt2p5bhk + ", mt2bhk=" + mt2bhk + ", mt1p5bhk=" + mt1p5bhk + ", mt1bhk=" + mt1bhk
				+ ", mt1rk=" + mt1rk + ", mttype=" + mttype + ", extraValues=" + extraValues + ", createdby="
				+ createdby + ", createdon=" + createdon + ", isactive=" + isactive + "]";
	}
}
