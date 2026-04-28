import { Footer } from './layout/HeaderAndFooter/Footer';
import './App.css'

//Do Auth0 later
//import bookstrap into the page for styling - check
//try first making a page where a bunch of "cards will display on the front of the screen, like the figma
//also could make a search bar that can display cards by names, or filter them by set, price, etc.

export const App = () => {
  return (
    <div className='d-flex flex-column min-vh-100'>
      <Footer />
    </div>
  );
};