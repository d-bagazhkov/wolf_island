import React from 'react';
import './Cell.css';

export default class Cell extends React.Component {

  render() {
    return (
        <div className="Cell" style={{width: this.props.size, height: this.props.size}}>
          {this.props.value}
        </div>
    );
  }

}