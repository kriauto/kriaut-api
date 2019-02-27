package ma.kriauto.api.response;

import java.util.List;

public class DetailOut {
	
	private List<ItemOut> byday;
	private List<ItemOut> byhour;
	
	public List<ItemOut> getByday() {
		return byday;
	}
	public void setByday(List<ItemOut> byday) {
		this.byday = byday;
	}
	public List<ItemOut> getByhour() {
		return byhour;
	}
	public void setByhour(List<ItemOut> byhour) {
		this.byhour = byhour;
	}
	
	@Override
	public String toString() {
		return "DetailOut [byday=" + byday + ", byhour=" + byhour + "]";
	}
}
