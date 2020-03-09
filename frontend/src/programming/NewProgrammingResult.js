import React, { Component } from 'react';
import { getAllGames } from "../util/APIUtils";
import { Select } from 'antd';
const { Option } = Select;

class NewProgrammingResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            games: [],
            selectedGame: "",
            validationError: ""
        }
    }

    componentDidMount() {
        getAllGames()
            .then(data => {
                let gamesFromAPI = data.map(game => {
                    return { value: game.id, display: game.name};
                });
                this.setState({
                    games: [{
                        value: 0,
                        display: "(Select a game)"
                    }].concat(gamesFromAPI)
                });
            })
            .catch(error => {
                console.log(error);
            });
    }

    handleChange(value, event) {
        console.log(`selected ${value}`);
        console.log(`Event ${event.id}`);
        this.setState({
            selectedGame: value
        });
    }

    render() {
        return (
            <div className="programming-container">
                <h1 className="page-title">New result</h1>
                <Select
                    value={this.state.selectedGame}
                        onSelect={(value, key) => this.handleChange(value, key)}
                >
                    {this.state.games.map(game => (
                        <Option
                            key = {game.value}
                            value = {game.display}
                        >
                            {game.display}
                        </Option>
                    ))}
                </Select>
                <div>
                    {this.state.validationError}
                </div>
                <div>
                    {this.state.selectedGame}
                </div>
            </div>
        )
    }
}

export default NewProgrammingResult;
