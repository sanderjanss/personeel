package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultJobTitelService implements JobtitelService {

    private JobtitelRepository jobtitelRepository;

    public DefaultJobTitelService(JobtitelRepository jobtitelRepository) {
        this.jobtitelRepository = jobtitelRepository;
    }

    public List<Jobtitel> findAll(){
        return jobtitelRepository.findAll();
    }

    @Override
    public Optional<Jobtitel> findById(long id) {
        return jobtitelRepository.findById(id);
    }
}
