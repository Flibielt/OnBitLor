import React, { Component } from 'react';
import { getAllGames } from '../util/APIUtils';
import LoadingIndicator  from '../common/LoadingIndicator';
import { Button } from 'antd';
import { SmileOutlined } from '@ant-design/icons';
import { withRouter } from 'react-router-dom';
import './GameList.css';
import {GAME_LIST_SIZE} from "../constants";

class GameList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            polls: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadGameList = this.loadGameList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadGameList(page = 0, size = GAME_LIST_SIZE) {
        let promise;
        promise = getAllGames(page, size);


        if(!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {
                const polls = this.state.polls.slice();
                const currentVotes = this.state.currentVotes.slice();

                this.setState({
                    games: polls.concat(response.content),
                    page: response.page,
                    size: response.size,
                    totalElements: response.totalElements,
                    totalPages: response.totalPages,
                    last: response.last,
                    currentVotes: currentVotes.concat(Array(response.content.length).fill(null)),
                    isLoading: false
                })
            }).catch(error => {
            this.setState({
                isLoading: false
            })
        });

    }

    componentDidMount() {
        this.loadGameList();
    }

    componentDidUpdate(nextProps) {
        if(this.props.isAuthenticated !== nextProps.isAuthenticated) {
            // Reset State
            this.setState({
                games: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                isLoading: false
            });
            this.loadGameList();
        }
    }

    handleLoadMore() {
        this.loadGameList(this.state.page + 1);
    }

    render() {
        return (
            <div className="games-container">
                {
                    !this.state.isLoading && this.state.polls.length === 0 ? (
                        <div className="no-games-found">
                            <span>No Polls Found.</span>
                        </div>
                    ): null
                }
                {
                    !this.state.isLoading && !this.state.last ? (
                        <div className="load-more-games">
                            <Button type="dashed" onClick={this.handleLoadMore} disabled={this.state.isLoading}>
                                <SmileOutlined type="plus" /> Load more
                            </Button>
                        </div>): null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator />: null
                }
            </div>
        );
    }
}

export default withRouter(GameList);
