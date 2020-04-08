//package editor.mcm.fhooe.at;

import java.util.ArrayList;


public class DataObservable {
	ArrayList<DataObserver> observers = new ArrayList<DataObserver>();
	public void addObserver(DataObserver _do) {
		observers.add(_do);
	}
}
