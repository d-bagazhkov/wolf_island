import React from 'react';
import "./ToggleSwitch.css";

export default class ToggleSwitch extends React.Component {

  render() {
    let {offValue, onValue, callback} = this.props;
    return (
        <div className="ToggleSwitch">
          <label className="checked value">{offValue}</label>
          <input type="checkbox" id="toggle" onChange={callback} className="checkbox"/>
          <label htmlFor="toggle" className="switch"/>
          <label className="value">{onValue}</label>
        </div>
    );
  }

}