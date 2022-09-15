import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        
        // variables declaration
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        
        boolean powerFlag = false;
        boolean divideFlag = false;
        boolean subtractFlag = false;
        boolean rootFlag = false;
        
        if (bottom.compareTo(INT_LIMIT) <= 0) {
            powerFlag = true;
        }
        if (!bottom.isZero()) {
            divideFlag = true;
        }
        if (bottom.compareTo(top) <= 0) {
            subtractFlag = true;
        }
        if (bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0) {
            rootFlag = true;
        }
        
        // Update View
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
        view.updatePowerAllowed(powerFlag);
        view.updateDivideAllowed(divideFlag);
        view.updateSubtractAllowed(subtractFlag);
        view.updateRootAllowed(rootFlag);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to the event
        top.copyFrom(bottom);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to this event
        top.add(bottom);
        bottom.transferFrom(top);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to the event
        top.subtract(bottom);
        bottom.transferFrom(top);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
     }

    @Override
    public void processMultiplyEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to the event
        top.multiply(bottom);
        bottom.transferFrom(top);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to the event
        NaturalNumber reminder = top.divide(bottom);
        bottom.transferFrom(top);
        top.transferFrom(reminder);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        int i = bottom.toInt();
        
        // Update model in response to the event
        top.power(i);
        bottom.transferFrom(top);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {
        
        // Aliasing to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        
        int i = bottom.toInt();
        
        // Update model in response to the event
        top.power(i);
        bottom.transferFrom(top);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        
        // Aliasing to top and bottom from model
        NaturalNumber bottom = this.model.bottom();
        
        // Update model in response to the event
        bottom.multiplyBy10(digit);
        
        // Update view to change in the model
        updateViewToMatchModel(this.model, this.view);
}

}
