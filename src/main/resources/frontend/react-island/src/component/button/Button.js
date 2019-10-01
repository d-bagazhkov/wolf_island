import React from 'react';
import './Button.css';

export default class Button extends React.Component {

  render() {
    return <input className={"Button"} type="button" value={this.props.value} />;
  }

}