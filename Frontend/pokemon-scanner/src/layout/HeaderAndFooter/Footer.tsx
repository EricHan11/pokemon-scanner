export const Footer = () => {
  return (
    <div className='main-color'>
        <footer className='container d-flex flex-wrap 
        justify-content-between align-items-center py-5 main-color'>
            <p className='mb-0 text-white'>Cards: Card Count</p>
            <p className='mb-0 text-white'>Collection: Collection Value</p>
            <p className='mb-0 text-white'>Balance: Balance Value</p>

            <button onClick={() => {}} className="btn btn-primary rounded">
                <span className="text-lg">+</span>
                Add Card
            </button>
        </footer>
    </div>
  );
};