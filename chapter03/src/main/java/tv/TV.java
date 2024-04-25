package tv;

public class TV {
	private int volume; // 0 ~ 10
	private int channel;  // 1 ~ 255
	private boolean power;
	
	public TV(int volume, int channel, boolean power) {
		this.volume = volume;
		this.channel = channel;
		this.power = power;
		
	}
	
	public int getVolume() {
		return volume;
	}
	
	public int getChannel() {
		return channel;
	}
	
	public boolean getPower() {
		return power;
	}
	
	
	public void power(boolean on) {
		if (on) {
			this.power = true;
		} else {
			this.power = false;
		}
	}
	
	public void channel(int channel) {
		if (channel > 255 ) {
			this.channel = 255;
		} 
		
		if (channel < 1) {
			this.channel = 1;
		}
		
		this.channel = channel;
	}
	
	public void channel(boolean up) {
		if (up) {
			this.channel++;
		}
	}
	
	public void volume(int volume) {
		if (volume > 100) {
			this.volume = 100;
		}
		if (volume < 0) {
			this.volume = 0;
		}
		this.volume = volume;
	}
	
	public void volume(boolean up) {
		if (up) {
			this.volume++;
		}
	}
	
	public void status() {
		if (power) {
			
		} else {
			
		}
		System.out.println();
	}

}
