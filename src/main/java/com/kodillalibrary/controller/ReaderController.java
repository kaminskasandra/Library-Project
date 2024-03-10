package com.kodillalibrary.controller;

import com.kodillalibrary.Dto.ReaderDto;
import com.kodillalibrary.domain.Reader;
import com.kodillalibrary.service.ReaderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/book-reader")
public class ReaderController {

    private final ReaderService readerService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Reader addReader(@RequestBody ReaderDto readerDto) {
        return readerService.addReader(modelMapper.map(readerDto, Reader.class));
    }

    @DeleteMapping()
    public void deleteReader(@RequestParam Long id) {
        readerService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ReaderDto findById(@PathVariable Long id) {
        Optional<Reader> readerOptional = readerService.findById(id);

        if (readerOptional.isPresent()) {
            Reader reader = readerOptional.get();
            return modelMapper.map(reader, ReaderDto.class);
        } else {
            throw new ErrorHandler("Book title not found with id: " + id);
        }
    }

    @GetMapping
    public List<ReaderDto> findAll() {
        List<Reader> readers = readerService.findAll();

        return readers.stream()
                .map(reader -> modelMapper.map(reader, ReaderDto.class))
                .toList();
    }
}
