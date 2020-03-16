import React, { Component } from 'react';
import { getAllUser } from '../../util/APIUtils';
import UserInfo from './UserInfo';
import LoadingIndicator  from '../../common/LoadingIndicator';
import { withRouter } from 'react-router-dom';

class UserInfoList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadUserInfoList = this.loadUserInfoList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadUserInfoList() {
        let promise = getAllUser();

        if (!promise) {
            return null;
        }

        this.setState({
            isLoading: true
        });

        promise.then(response => {
            const users = this.state.users.slice();

            this.setState({
                users: users.concat(response),
                page: response.page,
                size: response.size,
                totalElements: response.totalElements,
                totalPages: response.totalPages,
                last: response.last,
                isLoading: false
            })
        }).catch(error => {
            this.setState({
                isLoading: false
            })
        })
    }

    componentDidMount() {
        this._mounted = true;
        this.loadUserInfoList();
    }

    componentWillUnmount () {
        this._mounted = false
    }

    setState(state, callback) {
        if (this._mounted) {
            super.setState(state, callback);
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(this.props.isAuthenticated !== prevProps.isAuthenticated) {
            // Reset State
            this.setState({
                users: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                isLoading: false
            });
            this.loadUserInfoList();
        }
    }

    handleLoadMore() {
        this.loadProgrammingList(this.state.page + 1);
    }

    render() {
        const userViews = [];
        this.state.users.forEach((user, programmingIndex) =>{
            userViews.push(<UserInfo
                key={user.id}
                user={user}
            />)
        });

        return (
            <div className="programmings-container">
                {userViews}
                {
                    !this.state.isLoading && this.state.users.length === 0 ? (
                        <div className="no-programmings-found">
                            <span>No users Found.</span>
                        </div>
                    ): null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator />: null
                }
            </div>
        );
    }
}

export default withRouter(UserInfoList);
