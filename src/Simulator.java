import gui.GUISimulator;
import gui.GraphicalElement;
import gui.Simulable;

public abstract class Simulator implements Simulable {

    private Agent[] system;
    private Agent[] initialSystem;
    private GUISimulator window;

    public Simulator(Agent[] system, GUISimulator window) {
        this.system = new Agent[system.length];
        this.initialSystem = new Agent[system.length];
        this.window = window;

        for (int i = 0; i < system.length; i++) {
            this.system[i] = system[i];
            this.initialSystem[i] = system[i];
        }
    }

    public void draw_system(GraphicalElement graphicalElement) {
        window.reset();

        for (Agent a : this.system) {
            window.addGraphicalElement(graphicalElement);
        }
    }

    @Override
    public void next() {
        
    }

    @Override
    public void restart() {

    }
}
