import java.util.HashSet;

import models.Religion;

public class Mylist extends HashSet<Religion>{

	@Override
	public boolean add(Religion e) {
		// TODO Auto-generated method stub
		System.out.println("ajouter");
		if(!contains(e))
			return super.add(e);
		else
			return false;
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		System.out.println("ok");
		return super.contains(o);
	}
	

}
