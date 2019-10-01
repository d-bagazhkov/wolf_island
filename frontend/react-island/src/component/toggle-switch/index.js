import React from 'react';
import "./ToggleSwitch.css";

export default class ToggleSwitch extends React.Component {

  constructor(props) {
    super(props);
    let {offValue} = this.props;
    this.state = {value: offValue, toggleChecked: false}
  }


  render() {
    if (this.refs.switch) {
      if (this.state.toggleChecked)
        this.refs.switch.classList.add("checked");
      else
        this.refs.switch.classList.remove("checked");
    }
    return (
        <div className="ToggleSwitch">
          <label
              className="switch-label"
              onClick={this.toggleOnChange}
          >
            <span className="switch-button" ref="switch"/>
          </label>
          <label className="info">{this.state.value}</label>
        </div>
    );
  }

  toggleOnChange = (e) => {
    let {offValue, onValue, onChange} = this.props;
    let curCheck = !this.state.toggleChecked;
    onChange && onChange(curCheck);
    this.setState({
      toggleChecked: curCheck,
      value: curCheck ? onValue : offValue
    });
  }
}