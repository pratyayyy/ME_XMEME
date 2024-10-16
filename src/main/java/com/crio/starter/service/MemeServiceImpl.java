package com.crio.starter.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.starter.exchange.MemeRequestDTO;
import com.crio.starter.exchange.MemeRequestIdDTO;
import com.crio.starter.repositoryService.MemeRepositoryService;
import com.crio.starter.data.MemeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AccessLevel;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService {

    @Autowired
	private MemeRepositoryService memeRepositoryService;

    private ModelMapper modelMapper = new ModelMapper();

    public MemeServiceImpl() {
		super();
		modelMapper.getConfiguration().setFieldMatchingEnabled(false)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setSkipNullEnabled(true);
	}

	@Override
	public Optional<MemeRequestIdDTO> saveMemes(MemeRequestDTO memeOb){
		Optional<MemeRequestIdDTO> memeIdDTO = Optional.empty();

		try{
			MemeEntity meme = modelMapper.map(memeOb,MemeEntity.class);
			MemeEntity savedMeme = memeRepositoryService.saveMemes(meme);
			MemeRequestIdDTO dto = modelMapper.map(savedMeme, MemeRequestIdDTO.class);
			memeIdDTO =  Optional.of(dto);
		}	
		catch (RuntimeException e) {
			
		}
		return memeIdDTO;
	}
	

	@Override
	public Optional<MemeRequestDTO> getMemeById(String Id){

		Optional<MemeEntity> meme = memeRepositoryService.getMemeById(Id);
		Optional<MemeRequestDTO> memeDTO = Optional.empty(); 

		if (meme.isPresent()) {
			MemeRequestDTO map = modelMapper.map(meme.get(), MemeRequestDTO.class);
			memeDTO = Optional.of(map);
		}
		return memeDTO;
	}

	@Override
	public List<MemeRequestDTO> getLastNMemes(int N){
		List<MemeEntity> list = memeRepositoryService.getLastNMemes(N);
		List<MemeRequestDTO> memes = new ArrayList<>();

		for(MemeEntity memeEntity: list){
			MemeRequestDTO map = modelMapper.map(memeEntity, MemeRequestDTO.class);
			memes.add(map);
		}	
		return memes;
	}
}
