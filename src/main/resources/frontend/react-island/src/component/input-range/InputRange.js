import React from 'react';
import './InputRange.css'

export default class InputRange extends React.Component {

  state = {
    value: 0,
    labelValue: "Off"
  };

  render() {
    return (
        <div className="InputRange">
          <label className="value info">{this.state.labelValue}</label>
          <input
              type="range"
              min="0" max="10000"
              value={this.state.value}
              onChange={this.rangeOnChange.bind(this)}
              onInput={this.rangeOnInput.bind(this)}
              step="100"
          />
        </div>
    );
  }

  rangeOnChange({target}) {
    let {value} = target;
    this.setState({value});
  }

  rangeOnInput({target}) {
    let {value} = target;
    let labelValue = +value ? +value / 1000 + ' second' : "Off";
    this.setState({labelValue});
  }

}