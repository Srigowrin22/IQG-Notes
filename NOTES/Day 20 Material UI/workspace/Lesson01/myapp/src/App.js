import RadioGroup from '@mui/material/RadioGroup';
import './App.css';
import ComboBox from './components/Demo01-Autocomplete';
import BasicButtons from './components/Demo02-Button';
import BasicButtonGroup from './components/Demo03-ButtonGroup';
import Checkboxes from './components/Demo04-CheckBox';
import FloatingActionButtons from './components/Demo05-FAB';
import RadioButtonsGroup from './components/Demo06-RadioGroup';
import BasicRating from './components/Demo07-Rating';
import BasicSelect from './components/Demo08-Select';
import ContinuousSlider from './components/Demo09-Slider';
import BasicTextFields from './components/Demo10-TextField';
import TransferList from './components/Demo11-TransferList';
import ToggleButtons from './components/Demo12-ToggleButton';

import ImageAvatars from './components/Datadisplay/Demo01-Avatar';
import SimpleBadge from './components/Datadisplay/Demo02-Badge';
import BasicChips from './components/Datadisplay/Demo03-Chips';
import ListDividers from './components/Datadisplay/Demo04-Divider';
import SvgIconsColor from './components/Datadisplay/Demo05-Icon';

import BasicAlerts from './components/Feedbacks/Demo01-Alerts';
import SimpleDialogDemo from './components/Feedbacks/Demo02-Dialogs';
import CircularColor from './components/Feedbacks/Demo03-CircularProgress';
import YouTube from './components/Feedbacks/Demo04-Skeleton';
import CustomizedSnackbars from './components/Feedbacks/Demo05-SnackBar';

import AccordionExpandIcon from './components/Surfaces/Demo01-Accordian';
import SearchAppBar from './components/Surfaces/Demo02-Appbar';
import OutlinedCard from './components/Surfaces/Demo03-Card';


function App() {
  return (
    <div className="App">
      <div>
        {/* <div>
          <input type="button" value="Hello React"/>
        </div> */}
        <br></br>
        {/* <div>
          <Button variant="contained">Hello React</Button>
        </div>
  */}

        {/* Components */}
        <ComboBox></ComboBox>
        <BasicButtons></BasicButtons>
        <BasicButtonGroup></BasicButtonGroup>
        <Checkboxes></Checkboxes>
        <FloatingActionButtons></FloatingActionButtons>
        <RadioButtonsGroup></RadioButtonsGroup>
        <BasicRating></BasicRating>
        <BasicSelect></BasicSelect>
        <ContinuousSlider></ContinuousSlider>
        <BasicTextFields></BasicTextFields>
        <TransferList></TransferList>
        <ToggleButtons></ToggleButtons>
        <hr/>

        {/* Avatars */}
        <ImageAvatars></ImageAvatars>
        <SimpleBadge></SimpleBadge>
        <BasicChips></BasicChips>
        <ListDividers></ListDividers>
        <SvgIconsColor></SvgIconsColor>
        <hr/>

        {/* Feedbacks */}
        <BasicAlerts></BasicAlerts>
        <SimpleDialogDemo></SimpleDialogDemo>
        <CircularColor></CircularColor>
        <YouTube></YouTube>
        <CustomizedSnackbars></CustomizedSnackbars>
        <hr/>

        {/* Surfaces */}
        <AccordionExpandIcon></AccordionExpandIcon>
        <SearchAppBar></SearchAppBar>
        <OutlinedCard></OutlinedCard>
      </div>
    </div>
  );
}

export default App;
