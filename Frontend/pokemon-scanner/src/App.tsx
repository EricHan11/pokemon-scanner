import { Footer } from './layout/HeaderAndFooter/Footer';
import './App.css'
import { Redirect, Route, Switch, useHistory } from 'react-router-dom';
import { auth0Config } from './layout/lib/auth0Config';
import { Auth0Provider, withAuthenticationRequired } from '@auth0/auth0-react';
import { Header } from './layout/HeaderAndFooter/Header';

//try first making a page where a bunch of "cards will display on the front of the screen, like the figma
//also could make a search bar that can display cards by names, or filter them by set, price, etc.
const Auth0ProviderWithHistory = ({ children }: { children: React.ReactNode }) => {
  const history = useHistory();

  const onRedirectCallback = (appState: any) => {
    history.push(appState?.returnTo || "/home");
  };

  return (
    <Auth0Provider
      domain={auth0Config.domain}
      clientId={auth0Config.clientId}
      authorizationParams={{
        redirect_uri: auth0Config.redirectUri,
        audience: auth0Config.audience,
        scope: auth0Config.scope,
      }}
      onRedirectCallback={onRedirectCallback}
    >
      {children}
    </Auth0Provider>
  );
};

export const App = () => {
  return (
    <div className='d-flex flex-column min-vh-100'>
      <Auth0ProviderWithHistory>
        <Header />
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>
        <p>Cards go here</p>

        <div className="position-fixed bottom-0 start-0 w-100">
          <Footer />
        </div>
      </Auth0ProviderWithHistory>
    </div>
  );
};