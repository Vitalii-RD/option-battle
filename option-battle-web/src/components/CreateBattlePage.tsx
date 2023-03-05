import React, { FC, useState } from 'react'
import {
  Box,
  Container, FormControl,
  FormControlLabel, FormLabel, Radio,
  RadioGroup,
  TextField,
  Typography
} from '@mui/material'

interface AccessSetting {
  value: string
  description: string
}

const accessSettings: AccessSetting[] = [
  {
    value: 'public',
    description: 'Public - All users can play'
  },
  {
    value: 'Private',
    description: 'Private - No other users can access it'
  },
  {
    value: 'Protected',
    description: 'Protected - You can play after entering the password'
  }
]

const CreateBattlePage: FC = () => {
  const [title, setTitle] = useState<string>('')
  const [description, setDescription] = useState<string>('')

  const [accessSetting, setAccessSetting] = useState<string>('')

  const handleTitle = (event: React.ChangeEvent<HTMLInputElement>): void => { setTitle(event.target.value) }

  const handleDescription = (event: React.ChangeEvent<HTMLInputElement>): void => { setDescription(event.target.value) }

  const handleAccessSetting = (event: React.ChangeEvent<HTMLInputElement>): void => { setAccessSetting(event.target.value) }

  return (
    <Container >
      <Typography variant={'h4'}>Creating your battle</Typography>
      <Box>
        <TextField variant="outlined" label="Title" required type="text" fullWidth margin="normal"
                   value={title} onChange={handleTitle}/>
        <TextField variant="outlined" label="Description" type="text" fullWidth multiline rows={3} margin="normal"
          value={description} onChange={handleDescription}
        />

        <FormControl margin="normal">
          <FormLabel>Access setting</FormLabel>
          <RadioGroup
            value={accessSetting}
            onChange={handleAccessSetting}
          >
            {
              accessSettings.map((setting) => (
                <FormControlLabel key={setting.value} value={setting.value} control={<Radio/>} label={setting.description}/>
              ))
            }
          </RadioGroup>
        </FormControl>
      </Box>
    </Container>

  )
}

export default CreateBattlePage
