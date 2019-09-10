import React from 'react';

export default class ControlPanel extends React.Component {

  state = {
    rangeValue: "Off"
  };

  render() {
    return (
        <div className="control-panel">
          <section className="range-input">
            <input type="range"
                   min="0"
                   max="10000"
                   value="0"
                   step="100"
                   onInput={this.inputRangeValue.bind(this)}
                   onChange={this.changeRangeInput.bind(this)}/>
            <label className="info value">{this.state.rangeValue}</label>
          </section>
          <section className="reset-button">
            <button className="reset" name="Reset">Reset</button>
            <label className="info alarm"/>
          </section>
          <section className="toggle-switch">
            <div>
              <label className="switch">
                <input type="checkbox"/>
                <span className="slider round"/>
              </label>
              <label className="info value"/>
            </div>
            <label className="info alarm"/>
          </section>
        </div>
    );
  }

  changeRangeInput = (event) => {
    console.log("Value: ", event.target.value);
    this.props.callback(+event.target.value);
  };

  inputRangeValue(event) {
    if (+event.target.value)
      this.setState({rangeValue: event.target.value});
    else
      this.setState({rangeValue: "Off"});
  }
}