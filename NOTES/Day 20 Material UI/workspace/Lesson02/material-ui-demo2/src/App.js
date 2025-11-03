// import logo from './logo.svg';
import './App.css';

import SimpleBottomNavigation from './components/Navigation/Demo01-BottomNavigation';
import BasicBreadcrumbs from './components/Navigation/Demo02-BreadCrumbs';
import TemporaryDrawer from './components/Navigation/Demo03-Drawer';
import BasicMenu from './components/Navigation/Demo05-Menu';
import BasicPagination from './components/Navigation/Demo06-Pagination';
import BasicSpeedDial from './components/Navigation/Demo07-SpeedDial';
import HorizontalLinearStepper from './components/Navigation/Demo08-Stepper';

import BoxComponent from './components/Layout/Demo01-BoxLayout';
import SimpleContainer from './components/Layout/Demo02-FluidContainer';
import FixedContainer from './components/Layout/Demo03-FixedContainer';
import BasicGrid from './components/Layout/Demo04-GridLayoutOrFlexLayout';
import BasicStack from './components/Layout/Demo05-StackLayout';
import StandardImageList from './components/Layout/Demo06-ImageList';

function App() {
  return (
    <div className="App">

      {/* Navigation */}
      <SimpleBottomNavigation></SimpleBottomNavigation> <br />
      <BasicBreadcrumbs></BasicBreadcrumbs> <br />
      <TemporaryDrawer></TemporaryDrawer> <br />
      <BasicMenu></BasicMenu>
      <BasicPagination></BasicPagination>
      <BasicSpeedDial></BasicSpeedDial>
      <HorizontalLinearStepper></HorizontalLinearStepper>
      <StandardImageList></StandardImageList>

      <hr />

      {/* Layout */}
      <BoxComponent></BoxComponent>
      <SimpleContainer></SimpleContainer>
      <FixedContainer></FixedContainer>
      <BasicGrid></BasicGrid>
      <BasicStack></BasicStack>
    </div>
  );
}

export default App;
