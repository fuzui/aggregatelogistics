package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运费预估入参.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ExpressPriceParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 起始省份
	 */
	private String startProvince;
	/**
	 * 起始城市
	 */
	private String startCity;
	/**
	 * 起始区
	 */
	private String startDistrict;
	/**
	 * 起始详细地址
	 */
	private String startAddress;
	/**
	 * 目的省份
	 */
	private String endProvince;
	/**
	 * 目的城市
	 */
	private String endCity;
	/**
	 * 起始区
	 */
	private String endDistrict;
	/**
	 * 起始详细地址
	 */
	private String endAddress;
	/**
	 * 重量
	 */
	private BigDecimal weight;
	/**
	 * 长度
	 */
	private BigDecimal length;
	/**
	 * 宽度
	 */
	private BigDecimal width;
	/**
	 * 高度
	 */
	private BigDecimal height;
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getStartDistrict() {
		return startDistrict;
	}
	public void setStartDistrict(String startDistrict) {
		this.startDistrict = startDistrict;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndProvince() {
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEndDistrict() {
		return endDistrict;
	}
	public void setEndDistrict(String endDistrict) {
		this.endDistrict = endDistrict;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}