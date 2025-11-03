import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';
import Image1 from './images/Image1.jpg'

export default function ImageAvatars() {
  return (
    <Stack direction="row" spacing={2}>
      {/* <Avatar alt="Remy Sharp" src="Image1.jpg" /> */}
      <Avatar alt="Remy Sharp" src={Image1} />
      <Avatar alt="Travis Howard" src="/Data-Display/images/2.jpg" />
      <Avatar alt="Cindy Baker" src="/Data-Display/images/3.jpg" />
    </Stack>
  );
}