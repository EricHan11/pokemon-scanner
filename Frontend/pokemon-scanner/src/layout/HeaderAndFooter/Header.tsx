import { NavLink } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import React, { useEffect, useState } from "react";
//import { SpinnerLoading } from "../Utils/SpinnerLoading";

export const Header = () => {
    const { isAuthenticated, loginWithRedirect, logout, getIdTokenClaims } = useAuth0();
    //const [roles, setRoles] = useState<string[] | null>(null);
    //const [loading, setLoading] = useState(true); // Loading state to handle async data

    // if (loading) {
    //     return <SpinnerLoading />
    // }
    // useEffect(() => {
    //     const fetchRoles = async () => {
    //         const claims = await getIdTokenClaims();
    //         const fetchedRoles = claims?.['https://luv2code-react-library.com/roles'] || [];
    //         setRoles(fetchedRoles);
    //         setLoading(false); // Set loading to false once roles are loaded
    //     };

    //     fetchRoles();
    // }, [isAuthenticated, getIdTokenClaims]);

    const handleLogout = () => {
        console.log("handleLogout");
        logout({ logoutParams: { returnTo: window.location.origin } })
    };

    const handleLogin = () => {
        loginWithRedirect();
        window.location.assign("/");
    };

    return (
        <nav className='navbar navbar-expand-lg navbar-dark main-color py-3'>
            <div className='container-fluid'>
                <span className='navbar-brand'>Pokemon Scanner</span>
                {!isAuthenticated ?
                    <button className='btn btn-outline-light' onClick={handleLogin}>Sign in</button>
                    :
                    <button className='btn btn-outline-light' onClick={handleLogout}>Logout</button>
                }
            </div>
        </nav>
    );
};