import React from 'react';
import StatsPanel from "./panel/stats/StatsPanel.js";
import ControlPanel from "./panel/control/ControlPanel.js";

export default class ControlRoot extends React.Component {

  render() {
    let url = this.props.statsUrl;
    if (!url.endsWith("/"))
      url += "/";
    url += this.props.interval;
    return (
        <div className="control-root" >
          <StatsPanel statsUrl={url} />
          <ControlPanel callback={this.props.callback}/>
        </div>
    );
  }

}