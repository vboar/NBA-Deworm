package ui.controller;


public class ControllerFactory {
private static ControllerFactory controllerFactroy;
	
	private ControllerFactory() {}
	
	public static ControllerFactory getInstance(){
        if (controllerFactroy != null) {
            return controllerFactroy;
        }
        return new ControllerFactory();
	}
	
	public LiveController getliveController(){
		return new LiveController();
	}
	
}
