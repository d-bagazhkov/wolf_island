import React from 'react';
import LiStats from "./LiStats";

export default class StatsPanel extends React.Component {

  constructor(props, context) {
    super(props, context);
    this.state = {
      stats: []
    };
    const source = new EventSource(props.statsUrl);
    source.onmessage = function (event) {
      let stats = [];
      for (let [key, value] of Object.entries(event.data)) {
        stats.push(<LiStats key={key} kv={{key, value}}/>);
      }
      this.setState({stats})
    }
  }

  render() {
    return (
        <div className="stats">
          <ul>
            {this.state.stats}
          </ul>
        </div>
    );
  }

}