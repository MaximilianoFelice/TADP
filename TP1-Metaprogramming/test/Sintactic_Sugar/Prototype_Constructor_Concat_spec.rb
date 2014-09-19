require 'rspec'
require "TP1/Metaprogramming"

describe 'Build correctly concatenated prototypes' do

  it 'should create a new mixed constructor' do

    NewConstructor = TP::PrototypedConstructor.create do
      self.methodin_ = proc{50}
      self.prop = 50
      self.another_prop = 60
    end .with().with proc{|foo, bar| foo.prop = bar}

    new_object = NewConstructor.new({foo:50, bar: 40}, 10)

    expect(new_object.respond_to?(:methodin)).to eq(true)
    expect(new_object.prop).to eq(10)
    expect(new_object.another_prop).to eq(60)
    expect(new_object.foo).to eq(50)
    expect(new_object.bar).to eq(40)

  end
end