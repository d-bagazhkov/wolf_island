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
              className="range"
              type="range"
              min="0" max="10000"
              onChange={this.rangeOnChange}
              onInput={this.rangeOnInput}
              step="100"
          />
        </div>
    );
  }

  rangeOnChange = ({target}) => {
    let {value} = target;
    this.props.onChange && this.props.onChange(+value);
  };

  rangeOnInput = ({target}) => {
    let {value} = target;
    let labelValue = +value ? +value / 1000 + ' second' : "Off";
    this.setState({labelValue, value});
  };

}