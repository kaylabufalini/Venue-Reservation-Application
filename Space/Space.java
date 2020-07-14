package com.techelevator.Space;

import java.math.BigDecimal;

public class Space {
	
	private int spaceId;
	private String spaceName;
	private boolean isAccessible;
	private String openFrom;
	private String openTo;
	private BigDecimal dailyRate;
	private int maxOccupancy;
	
	
	public int getSpaceId() {
		return spaceId;
	}
	
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	
	public String getSpaceName() {
		return spaceName;
	}
	
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	public boolean isAccessible() {
		return isAccessible;
	}
	
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	
	public String getOpenFrom() {
		return openFrom;
	}
	
	public void setOpenFrom(int openFrom) {
		
		this.openFrom = returnMonth(openFrom);
		
	}
	
	public String getOpenTo() {
		return openTo;
	}
	
	public void setOpenTo(int openTo) {
		
		this.openTo = returnMonth(openTo);
		
	}
	
	public BigDecimal getDailyRate() {
		return dailyRate;
	}
	
	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	private String returnMonth (int date) {
		
		if (date == 1) {
			return "Jan.";
		}
		
		else if (date == 2) {
			return "Feb.";
		}
		
		else if (date == 3) {
			return "Mar.";
		}
		
		else if (date == 4) {
			return "Apr.";
		}
		
		else if (date == 5) {
			return "May";
		}
		
		else if (date == 6) {
			return "Jun.";
		}
		
		else if (date == 7) {
			return "Jul.";
		}
		
		else if (date == 8) {
			return "Aug.";
		}
		
		else if (date == 9) {
			return "Sep.";
		}

		else if (date == 10) {
			return "Oct.";
		}
		
		else if (date == 11) {
			return "Nov.";
		}
		
		else  {
			return "Dec.";
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dailyRate == null) ? 0 : dailyRate.hashCode());
		result = prime * result + (isAccessible ? 1231 : 1237);
		result = prime * result + maxOccupancy;
		result = prime * result + ((openFrom == null) ? 0 : openFrom.hashCode());
		result = prime * result + ((openTo == null) ? 0 : openTo.hashCode());
		result = prime * result + spaceId;
		result = prime * result + ((spaceName == null) ? 0 : spaceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Space other = (Space) obj;
		if (dailyRate == null) {
			if (other.dailyRate != null)
				return false;
		} else if (!dailyRate.equals(other.dailyRate))
			return false;
		if (isAccessible != other.isAccessible)
			return false;
		if (maxOccupancy != other.maxOccupancy)
			return false;
		if (openFrom == null) {
			if (other.openFrom != null)
				return false;
		} else if (!openFrom.equals(other.openFrom))
			return false;
		if (openTo == null) {
			if (other.openTo != null)
				return false;
		} else if (!openTo.equals(other.openTo))
			return false;
		if (spaceId != other.spaceId)
			return false;
		if (spaceName == null) {
			if (other.spaceName != null)
				return false;
		} else if (!spaceName.equals(other.spaceName))
			return false;
		return true;
	}
	
	

}
